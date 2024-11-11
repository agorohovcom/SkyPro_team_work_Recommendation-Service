package org.sky_pro.team_work;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class TeamWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamWorkApplication.class, args);
    }

}

//todo в application.yaml прописать свои настройки подключения к бд
//spring:
//datasource:
//url: jdbc:postgresql://localhost:5432/recommendationDB - свои настройки
//username:  student - свои настройки
//password:  chocolatefrog - свои настройки
//liquibase:
//change-log: classpath:db/liquibase/changelog-master.yml