package com.gym.crm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.findAndRegisterModules();
    }

    @SneakyThrows
    public static <T> List<T> deserializeAsList(InputStream is, Class<T> valueType) {
        return MAPPER.readValue(is, MAPPER.getTypeFactory().constructCollectionType(List.class, valueType));
    }
}