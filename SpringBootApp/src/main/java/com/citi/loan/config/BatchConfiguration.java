package com.citi.loan.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.citi.loan.JobCompletionNotificationListener;
import com.citi.loan.model.Person;
import com.citi.loan.processor.PersonItemProcessor;

//@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	/*
	 * @Bean public Job processJob() { return jobBuilderFactory.get("processJob")
	 * .incrementer(new RunIdIncrementer()) .listener(listener())
	 * .flow(processStep1()) .end().build(); }
	 * 
	 * @Bean public Step processStep1() { return
	 * stepBuilderFactory.get("processStep1").<String,String> chunk(1) .reader(new
	 * Reader()) .processor(new Processor()) .write(new Writer()) .build(); }
	 */
	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
				.resource(new ClassPathResource("sample-data.csv")).delimited()
				.names(new String[] { "firstName", "lastName" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				}).build();
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	/*
	 * @Bean public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
	 * return new JdbcBatchItemWriterBuilder<Person>()
	 * .itemSqlParameterSourceProvider(new
	 * BeanPropertyItemSqlParameterSourceProvider<>())
	 * .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)"
	 * ) .dataSource(dataSource) .build(); }
	 */

	public FlatFileItemWriter<Person> writer() {
		return new FlatFileItemWriterBuilder<Person>().name("personItemWriter")
				.resource(new FileSystemResource("C:\\Users\\Pranay\\csample-output.txt")).delimited()
				.names(new String[] { "firstName", "lastName" }).build();

	}
	
	/*
	 * @Bean public Job processJob() { return jobBuilderFactory.get("processJob")
	 * .incrementer(new RunIdIncrementer()) .listener(listener())
	 * .flow(processStep1()) .end().build(); }
	 * 
	 * @Bean public Step processStep1() { return
	 * stepBuilderFactory.get("processStep1").<String,String> chunk(1) .reader(new
	 * Reader()) .processor(new Processor()) .write(new Writer()) .build(); }
	 */
	
	@Bean
	public Job importUserJob() {
	  return jobBuilderFactory.get("importUserJob")
	    .incrementer(new RunIdIncrementer())
	    .listener(listener())
	    .flow(step1())
	    .end()
	    .build();
	}

	@Bean
	public Step step1(){
	  return stepBuilderFactory.get("step1")
	    .<Person, Person> chunk(10)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer())
	    .build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				System.out.println("Starting job successfully");

			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				System.out.println("Stopped job successfully");
			}
		};
	}

}
