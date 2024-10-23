package org.example.anonymchatbot.configuration.openapi;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi
                .builder()
                .group("all-apis")
                .packagesToScan("org.example.anonymchatbot")
                .build();
    }
}
