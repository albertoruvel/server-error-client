package com.messaging.error.client;

import com.messaging.error.client.data.ErrorMessageRequest;
import com.messaging.error.client.data.ErrorMessageResponse;
import com.messaging.error.client.service.ErrorMessageClient;
import com.messaging.error.client.service.ServiceManagerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class ApplicationTest {
    public static void main( String[] args ) throws IOException {
        Properties properties = new Properties();
        properties.load(ApplicationTest.class.getResourceAsStream("/properties/conf-properties.properties"));

        ErrorMessageClient errorMessageClient = ServiceManagerFactory.getErrorMessageClient(properties);
        ErrorMessageRequest request = new ErrorMessageRequest();
        request.setBody("Body from test");
        request.setApplicationId("com.messaging.error.client");
        request.setDate("00:00:00 00:00");
        errorMessageClient.sendErrorMessage(request).enqueue(new Callback<ErrorMessageResponse>() {
            @Override
            public void onResponse(Call<ErrorMessageResponse> call, Response<ErrorMessageResponse> response) {
                Logger.getGlobal().info(String.format("Message sent: %s", response.body().getSuccess()));
            }

            @Override
            public void onFailure(Call<ErrorMessageResponse> call, Throwable throwable) {
                Logger.getGlobal().severe(throwable.getMessage());
            }
        });
    }
}
