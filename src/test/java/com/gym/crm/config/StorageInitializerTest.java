
package com.gym.crm.config;

import com.gym.crm.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StorageInitializerTest {

    @Mock
    private Environment environment;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private Resource resource;

    @Mock
    private InMemoryStorage<Object> inMemoryStorage;

    @InjectMocks
    private StorageInitializer storageInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostProcessAfterInitialization() throws IOException {
        when(environment.getProperty(anyString())).thenReturn("some/path");
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        InputStream inputStream = new ByteArrayInputStream("[{\"id\":1,\"name\":\"test\"}]".getBytes());
        when(resource.getInputStream()).thenReturn(inputStream);
        when(inMemoryStorage.getValueType()).thenReturn(Object.class);
    }
        @Test
        void testPostProcessAfterInitialization_NotInMemoryStorageBean () {
            Object bean = new Object();
            Object result = storageInitializer.postProcessAfterInitialization(bean, "someBean");
            assertEquals(bean, result);
        }

        @Test
        void testPostProcessAfterInitialization_EmptyDataFile () throws IOException {
            when(environment.getProperty(anyString())).thenReturn("some/path");
            when(resourceLoader.getResource(anyString())).thenReturn(resource);
            InputStream inputStream = new ByteArrayInputStream("[]".getBytes());
            when(resource.getInputStream()).thenReturn(inputStream);
            when(inMemoryStorage.getValueType()).thenReturn(Object.class);

            storageInitializer.postProcessAfterInitialization(inMemoryStorage, "traineeDb");

            verify(inMemoryStorage, never()).put(any(), any());
        }
    }
