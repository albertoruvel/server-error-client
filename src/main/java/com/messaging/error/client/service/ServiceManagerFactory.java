package com.messaging.error.client.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Properties;

public class ServiceManagerFactory {

    private static Retrofit retrofitInstance;

    public static final String SEND_ERROR_CONTEXT_PATH = "error/send";
    private static final String HOST_PROPERTY_NAME = "com.messaging.host";


    public static ErrorMessageClient getErrorMessageClient(Properties properties)throws IllegalArgumentException{
        if(properties == null){
            throw new IllegalArgumentException("No properties to load");
        }

        if(retrofitInstance == null){
            final String hostBaseUrl = properties.getProperty(HOST_PROPERTY_NAME);
            if(hostBaseUrl == null || hostBaseUrl.isEmpty()){
                throw new IllegalArgumentException("Host property is null or empty");
            }
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(hostBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitInstance.create(ErrorMessageClient.class);
    }
}
