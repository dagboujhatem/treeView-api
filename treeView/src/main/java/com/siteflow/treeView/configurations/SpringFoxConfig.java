package com.siteflow.treeView.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 *
 *  Please visit this link for API documentation:
 *  Link of documentations : http://localhost:8080/api/v1/swagger-ui/index.html
 */


@Configuration
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(new LinkedHashSet<>(Arrays.asList("application/json", "application/xml")))
                .protocols(new HashSet<>(Arrays.asList("http","https")))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger APIs Documentation")
                .description("This page lists all the rest apis.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .termsOfServiceUrl("https://gitee.com/log4j/pig")
                .contact(new Contact("Dagbouj Hatem",
                        "https://github.com/dagboujhatem",
                        "dagboujhatem@gmail.com"))
                .build();
    }
}
