package com.redbee.persistor.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.redbee" )
@EnableMongoRepositories(basePackages = "com.redbee.persistor.repository")
public class PersistorConfiguration {

}
