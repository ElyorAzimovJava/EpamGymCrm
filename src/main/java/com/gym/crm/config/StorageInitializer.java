package com.gym.crm.config;

import com.gym.crm.storage.InMemoryStorage;
import com.gym.crm.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.gym.crm.config.StorageConfiguration.*;

@Slf4j
@Component
public class StorageInitializer implements BeanPostProcessor, EnvironmentAware, ResourceLoaderAware {

    private static final Map<String, String> BEAN_NAME_TO_PATH_PROPERTY_MAP = Map.of(
            TRAINER_DB_BEAN_NAME, "trainer.data.path",
            TRAINEE_DB_BEAN_NAME, "trainee.data.path",
            TRAINING_DB_BEAN_NAME, "training.data.path"
    );

    private ResourceLoader resourceLoader;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof InMemoryStorage<?> inMemoryStorageBean) {
            Resource initialData = getInitialDataResource(beanName);
            initializeStorage(inMemoryStorageBean, initialData);
        }
        return bean;
    }

    private Resource getInitialDataResource(String beanName) {
        String propertyName = BEAN_NAME_TO_PATH_PROPERTY_MAP.get(beanName);
        String environmentProperty = environment.getProperty(propertyName);
        Objects.requireNonNull(environmentProperty,
                "Can not resolve property " + propertyName + " for bean " + beanName);
        return resourceLoader.getResource(environmentProperty);
    }

    @SneakyThrows
    private <T> void initializeStorage(InMemoryStorage<T> storage, Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            List<T> records = JsonUtils.deserializeAsList(is, storage.getValueType());
            records.forEach(record -> storage.put(UUID.randomUUID(), record));
            log.info("Initialized storage from file: {} ({} records)", resource.getFilename(), records.size());
        }
    }
}