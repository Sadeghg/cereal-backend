package io.mars.cereal.batch;
import org.springframework.batch.core.*;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.stereotype.Component;

public class CleanupListener implements JobExecutionListener {

    private final FlatFileItemWriter<?> flatFileItemWriter;

    public CleanupListener(FlatFileItemWriter<?> flatFileItemWriter) {
        this.flatFileItemWriter = flatFileItemWriter;
    }


    @Override
    public void afterJob(JobExecution jobExecution) {
        flatFileItemWriter.close();
    }

}
