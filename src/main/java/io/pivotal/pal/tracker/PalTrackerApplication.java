package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {
    public static void main(String[] args){
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    /*@Bean
    public TimeEntryRepository timeEntryRepository(){
        return new InMemoryTimeEntryRepository();
    }*/

    MysqlDataSource dataSource = new MysqlDataSource();

    @Bean
    public TimeEntryRepository jdbcTimeEntryRepository(DataSource dataSource){
        return new JdbcTimeEntryRepository(dataSource);
    }

    //Add a @Bean method that returns an instance of ObjectMapper
    // that properly deserializes ISO-6801 dates into a LocalDate.
    @Bean
    public ObjectMapper jsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(new JavaTimeModule())
                .build();
    }
}
