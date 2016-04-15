package com.example.administrator.chatandroid.WebServices;

import android.os.AsyncTask;

import com.example.administrator.chatandroid.Model.Message;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/15/2016.
 */
public class RestPostMessage extends AsyncTask<String , Void, Message> {

    @Override
    protected Message doInBackground(String... params) {
        String url = "http://192.168.0.6:8191/rest/messages";
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        Message message = new Message();
        message.setFrom(Integer.parseInt(params[0]));
        message.setTo(Integer.parseInt(params[1]));
        message.setText(params[2]);
                // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Message postResponse = restTemplate.postForObject(url,message,Message.class);

        return postResponse;
    }
}
