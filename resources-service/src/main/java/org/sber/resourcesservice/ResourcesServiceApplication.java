package org.sber.resourcesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.sber")
@ComponentScan("com.sber")
public class ResourcesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourcesServiceApplication.class, args);
    }

}
