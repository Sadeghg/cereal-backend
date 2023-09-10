package io.mars.cereal.batch;

import io.mars.cereal.model.Category;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;


@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class BatchConfig {


    private final DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Category> jdbcCategoryReader() {
        JdbcCursorItemReader<Category> reader = new JpaCursorItemReader<>();
        reader.setSql("SELECT id, title, parent_id FROM category");
        reader.setRowMapper(new BeanPropertyRowMapper<>());
        reader.setDataSource(dataSource);
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Category> writer(){
        FlatFileItemWriter<Category> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/categories.csv"));
        DelimitedLineAggregator<Category> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<Category> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "title", "parent_id"});
        writer.setLineAggregator(aggregator);
        return writer;
    }

    @Bean
    public Step executeSteo(JobRepository jobRepository){
        return new StepBuilder("category-csv-step", jobRepository)
                .<>chunk(10).reader(jdbcCategoryReader()).processor(writer());
    }
}
