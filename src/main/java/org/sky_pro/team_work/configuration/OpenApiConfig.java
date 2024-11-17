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

                // Объединяем данные из openapi.yaml с автоматически сгенерированной документацией
                if (yamlOpenAPI.getPaths() != null) {
                    openApi.getPaths().putAll(yamlOpenAPI.getPaths());
                }
                if (yamlOpenAPI.getComponents() != null) {
                    if (openApi.getComponents() == null) {
                        openApi.setComponents(yamlOpenAPI.getComponents());
                    } else {
                        openApi.getComponents().getSchemas().putAll(yamlOpenAPI.getComponents().getSchemas());
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to load OpenAPI YAML", e);
            }
        };
    }
}