package com.management.sales.sales.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jsons {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String asJsonString(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException("Error converting object to json");
        }
    }

    public static <T> T objectFromString(Class<T> aClass, String value) {
        try{
            return objectMapper.readValue(value, aClass);
        }catch (Exception e){
            throw new RuntimeException("Error converting json to object");
        }
    }
}
