package com.oxahex.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan(basePackages = {"com.oxahex.domain.*"})      // TODO: 이거 Configuration 따로 뺄 수 있었던 것 같으니까 확인
@ComponentScan(basePackages = {"com.oxahex.*.*"})
@EnableJpaRepositories(basePackages = {"com.oxahex.domain.repository"})
@EntityScan(basePackages = {"com.oxahex.domain.entity"})
@SpringBootApplication
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}