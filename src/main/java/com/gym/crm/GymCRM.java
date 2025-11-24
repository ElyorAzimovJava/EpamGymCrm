package com.gym.crm;

import com.gym.crm.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class GymCRM {
    public static void main(String[] args) {
        log.info("Application starting...");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("Application context has been initialized");
    }
}