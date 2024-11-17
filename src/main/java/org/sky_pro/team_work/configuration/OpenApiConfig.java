package org.sky_pro.team_work.configuration;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.parser.core.models.ParseOptions;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
                .group("recommendations")
                .addOpenApiCustomizer(openApiCustomizer())
                .build();
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            try (InputStream inputStream = new ClassPathResource("api/openapi.yaml").getInputStream()) {
                OpenAPIParser parser = new OpenAPIParser();
                ParseOptions options = new ParseOptions();
                options.setResolve(true);
                io.swagger.v3.oas.models.OpenAPI yamlOpenAPI = parser.readContents(
                        new String(inputStream.readAllBytes()), null, options).getOpenAPI();

                openApi.paths(yamlOpenAPI.getPaths());
            } catch (Exception e) {
                throw new RuntimeException("Failed to load OpenAPI YAML", e);
            }
        };
    }
}