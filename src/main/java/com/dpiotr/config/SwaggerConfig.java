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
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWxwaW90ckBnbWFpbC5jb20iLCJleHAiOjE1MTY5MjkxNDV9.bmtBIktCVvt-KUyMHVF8Mbv1ZYxyUMyNDsUKAmrsQUR0p4Naozi_UnDcHmvtKUPcm-ENqbZrgESsLr_LIPG1_Q",
                ApiKeyVehicle.HEADER, "Authorization", ",");
    }
}