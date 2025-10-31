package com.gym.crm.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageInitializer implements BeanPostProcessor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("classpath:data/trainers.json")
    private Resource trainerDataResource;

    @Value("classpath:data/trainees.json")
    private Resource traineeDataResource;

    @Value("classpath:data/trainings.json")
    private Resource trainingDataResource;

    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            switch (beanName) {
                case "trainerDb" -> initializeStorage((Map<UUID, Object>) bean, trainerDataResource);
                case "traineeDb" -> initializeStorage((Map<UUID, Object>) bean, traineeDataResource);
                case "trainingDb" -> initializeStorage((Map<UUID, Object>) bean, trainingDataResource);
                default -> { }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize storage for: " + beanName, e);
        }

        return bean;
    }

    private void initializeStorage(Map<UUID, Object> storage, Resource resource) throws Exception {
        try (InputStream is = resource.getInputStream()) {
            List<Map<String, Object>> records = objectMapper.readValue(is, new TypeReference<>() {});
            for (Map<String, Object> record : records) {
                storage.put(UUID.randomUUID(), record);
            }

            log.info("Initialized storage from file: {} ({} records)", resource.getFilename(), records.size());
        }
    }
}
