package com.gym.crm.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StorageInitializer implements BeanPostProcessor {
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${trainer.data.path}")
    private String trainerDataPath;

    @Value("${trainee.data.path}")
    private String traineeDataPath;

    @Value("${training.data.path}")
    private String trainingDataPath;


    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            if (beanName.equals("trainerDb")) {
                initializeStorage((Map<UUID, Object>) bean, trainerDataPath);
            } else if (beanName.equals("traineeDb")) {
                initializeStorage((Map<UUID, Object>) bean, traineeDataPath);
            } else if (beanName.equals("trainingDb")) {
                initializeStorage((Map<UUID, Object>) bean, trainingDataPath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize storage for: " + beanName, e);
        }
        return bean;
    }

    private void initializeStorage(Map<UUID, Object> storage, String path) throws Exception {
        InputStream is = getClass().getResourceAsStream(path.replace("classpath:", "/"));
        if (is == null) {
            System.out.println("File not found: " + path);
            return;
        }

        List<Map<String, Object>> records = objectMapper.readValue(is, new TypeReference<>() {});
        for (Map<String, Object> record : records) {
            storage.put(UUID.randomUUID(), record);
        }

        System.out.println("Initialized storage from file: " + path + " (" + records.size() + " records)");
    }
}
