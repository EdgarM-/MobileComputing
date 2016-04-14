package com.example.administrator.chatandroid.WebServices;

import android.os.AsyncTask;

import com.example.administrator.chatandroid.Model.Contact;
import com.example.administrator.chatandroid.Model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 4/14/2016.
 */
public class RestGetMessages extends AsyncTask<Integer,Void,ArrayList<Message>> {
    @Override
    protected ArrayList<Message> doInBackground(Integer... params) {
        String url = "http://192.168.0.6:8191/rest/messages/{fromUserId}/{toUserId}";
        ArrayList<Message> messages = null;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, params[0],params[1]);
        try {

            ObjectMapper mapper = new ObjectMapper();
            messages = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Message.class));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
