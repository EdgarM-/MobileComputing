package com.example.administrator.chatandroid.WebServices;

import android.os.AsyncTask;

import com.example.administrator.chatandroid.Model.File;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by CarlosEnrique on 15/04/2016.
 */
public class RestGetFiles extends AsyncTask<Integer,Void,ArrayList<File>> {
    public static final String ip = "172.17.23.229";
    @Override
    protected ArrayList<File> doInBackground(Integer... params) {
        String url = "http://"+ip+":8191/rest/files/{fromUserId}/{toUserId}";
        ArrayList<File> files = null;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, params[0],params[1]);
        try {

            ObjectMapper mapper = new ObjectMapper();
            files = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(ArrayList.class,File.class));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }
}
