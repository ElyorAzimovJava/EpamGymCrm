package com.gym.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan(basePackages = "com.gym.crm")
public class Config {
    @Bean(name = "trainerDb")
    public Map<UUID, Object> trainerDb() {
        return new ConcurrentHashMap<>();
    }
    @Bean(name = "traineeDb")
    public Map<UUID, Object> traineeDb() {
        return new ConcurrentHashMap<>();
    }
    @Bean(name = "trainingDb")
    public Map<UUID, Object> trainingDb() {
        return new ConcurrentHashMap<>();
    }
}
