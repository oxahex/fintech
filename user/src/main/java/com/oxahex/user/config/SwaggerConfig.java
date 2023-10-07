package com.oxahex.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .groupName("user")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oxahex.user"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("USER API")
                .description("유저 정보 API")
                .version("1.0")
                .build();
    }

}
