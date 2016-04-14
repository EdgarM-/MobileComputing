package com.example.administrator.chatandroid.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/13/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetContacts {
    private ArrayList<Contact> data;

    public ArrayList<Contact> getData() {
        return data;
    }

    public void setData(ArrayList<Contact> contactos) {
        this.data = contactos;
    }
}
