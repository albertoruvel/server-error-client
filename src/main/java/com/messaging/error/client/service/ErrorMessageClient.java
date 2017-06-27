package com.messaging.error.client.service;

import com.messaging.error.client.data.ErrorMessageRequest;
import com.messaging.error.client.data.ErrorMessageResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ErrorMessageClient {

    /**
     * Sends a new error message to wildfly to be sent to an SQS queue
     * @param request
     * @return
     */
    @POST(value = ServiceManagerFactory.SEND_ERROR_CONTEXT_PATH)
    public Call<ErrorMessageResponse> sendErrorMessage(@Body  ErrorMessageRequest request);
}
