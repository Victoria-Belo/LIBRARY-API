package com.project.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Victoria
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        String api = "https://openlibrary.org";
        return WebClient.builder()
                .baseUrl(api)
                .build();
    }
}
