package com.example.administrator.chatandroid.WebServices;

import android.os.AsyncTask;

import com.example.administrator.chatandroid.Model.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 4/12/2016.
 */
public class RestGetContacts extends AsyncTask<Integer,Void,ArrayList<Contact>> {
    public static final String ip = "172.17.23.229";
    @Override
    protected ArrayList<Contact> doInBackground(Integer... params) {
        String url = "http://"+ip+":8191/rest/contacts/{uId}";
        ArrayList<Contact> contactos = null;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, params[0]);
        try {

            ObjectMapper mapper = new ObjectMapper();
            contactos = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(ArrayList.class,Contact.class));

            /*GetContacts contactList = new ObjectMapper().readValue(result,GetContacts.class);
            contactos = contactList.getData();
            */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactos;
    }
}
