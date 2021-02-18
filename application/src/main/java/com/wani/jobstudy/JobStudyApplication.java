package com.wani.jobstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.wani.domain")
@ComponentScan(basePackages = {"com.wani.jobstudy" , "com.wani.domain"})
@EnableJpaRepositories("com.wani.*")
@EnableJpaAuditing
public class JobStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobStudyApplication.class, args);
    }

}
