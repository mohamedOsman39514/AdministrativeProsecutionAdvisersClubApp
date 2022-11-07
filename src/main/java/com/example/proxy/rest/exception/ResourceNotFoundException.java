package com.example.proxy.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(generateMessage(resourceName, id.toString()));
    }

    public ResourceNotFoundException(String resourceName, String key) {
        super(generateMessage(resourceName, key));
    }


    private static String generateMessage(String resourceName, String key) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With Key = ").append(key).append(" Not Found.");
        return messageBuilder.toString();
    }

}