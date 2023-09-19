package io.mars.cereal.batch;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;

/*
 this files extracts data from SQL table to CSV files
 */

@Configuration
@AllArgsConstructor
public class BatchConfig {

    private final DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Map<String, Object>> jdbcCategoryReader() {
        JdbcCursorItemReader<Map<String, Object>> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id, title, parent_id FROM category");
        reader.setRowMapper((rs, rowNum) -> Map.of(
                "id", rs.getLong("id"),
                "title", rs.getString("title"),
                "parent_id", rs.getLong("parent_id")));
        return reader;
    }

    @Bean(name = "primary-category-csv")
    public FlatFileItemWriter<Map<String, Object>> writer() {
        FlatFileItemWriter<Map<String, Object>> writer = new FlatFileItemWriter<>();
        writer.setAppendAllowed(true);
        FileSystemResource resource = new FileSystemResource("src/main/resources/categories.csv");
        writer.setAppendAllowed(!isEmpty(resource)); writer.setResource(resource);
        DelimitedLineAggregator<Map<String, Object>> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<Map<String, Object>> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "title, parent_id"});
        writer.setLineAggregator(aggregator);
        return writer;
    }

    @Bean
    public Step executeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("category-sql-read", jobRepository)
                .<Map<String, Object>, Map<String, Object>>chunk(100, transactionManager)
                .reader(jdbcCategoryReader())
                .writer(writer()).build();
    }

    @Bean
    public Job proccessJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("import-categories", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(executeStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public CleanupListener cleanupListener(){
        return new CleanupListener(writer());
    }

    private boolean isEmpty(Resource resource) {
        try {
            return resource.getFile().length() == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
