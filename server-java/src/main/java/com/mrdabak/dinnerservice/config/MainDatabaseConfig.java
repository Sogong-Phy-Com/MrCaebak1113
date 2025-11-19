package com.mrdabak.dinnerservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackageClasses = {
        com.mrdabak.dinnerservice.repository.UserRepository.class,
        com.mrdabak.dinnerservice.repository.DinnerTypeRepository.class,
        com.mrdabak.dinnerservice.repository.MenuItemRepository.class,
        com.mrdabak.dinnerservice.repository.DinnerMenuItemRepository.class
    }
)
public class MainDatabaseConfig {
}

