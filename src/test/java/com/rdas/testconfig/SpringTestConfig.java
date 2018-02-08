package com.rdas.testconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@Configuration
@ComponentScan({"com.rdas"})
public class SpringTestConfig {
}
