package com.sg.gov.hdb.marvel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.sg.gov.hdb.marvel.repository")
public class RdsConfig {
}
