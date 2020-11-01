package com.example.iscc.ui.chat;

import com.example.iscc.notification.MyResponse;
import com.example.iscc.notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAd6saWW0:APA91bF_mGi8R6lRTEwchznMSUxj3AvbZ-KfuMHNVffZuURVmL4GHDH0z6qsq2EhimJXZAAHl5KXVEhT_qsyZA2KyZ7VAQIXVIjHJ_CSgr9jRULxuW0Ifqh9M7lMw6HV2FG7foBovG0M"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
