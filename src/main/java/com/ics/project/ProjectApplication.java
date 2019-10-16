package com.ics.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
//        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(ProjectApplication.class, args);
    }

//    public static void main(String[] args) {
//        SpringApplication springApp = new SpringApplication(ProjectApplication.class);
//        springApp.setAdditionalProfiles("dev");
//        springApp.run(args);
//    }
}
