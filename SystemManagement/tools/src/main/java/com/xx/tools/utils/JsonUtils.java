package com.xx.tools.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private JsonUtils() {}

    private final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String getJsonByObject(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getObjectByJson(String jsonText, Class<T> beanClass) {
        try {
            return MAPPER.readValue(jsonText, beanClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getListByJson(String jsonText, Class collectionClass, Class beanClass) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(collectionClass, beanClass);
            return MAPPER.readValue(jsonText, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getMapByJson(String jsonText, Class collectionClass, Class keyClass, Class valueClass) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(collectionClass, keyClass, valueClass);
            return MAPPER.readValue(jsonText, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
