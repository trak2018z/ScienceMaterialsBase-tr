package com.dpiotr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by dpiotr on 29.10.17.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SPRING_WEB)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiYWRtaW5pc3RyYXRvciJ9XSwic3ViIjoiYWRtaW5AYWRtaW4uY29tIiwiZXhwIjoxNTE3NjA1ODI1fQ.qiBUKOmR-x7TbVauyelurqFtJptKrFGep1oHTHlN6gupCB82jgG3YzF185hkTdqnu_qQHN8cKO2vLC8xVNSQSQ",
                ApiKeyVehicle.HEADER, "Authorization", ",");
    }
}