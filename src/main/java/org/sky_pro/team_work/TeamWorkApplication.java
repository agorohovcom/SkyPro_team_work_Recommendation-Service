package org.sky_pro.team_work;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.sky_pro.team_work.util.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition
@EnableCaching
public class TeamWorkApplication {

    public static void main(String[] args) {
        DotenvLoader.loadEnvironmentVariables();
        SpringApplication.run(TeamWorkApplication.class, args);
    }
}

