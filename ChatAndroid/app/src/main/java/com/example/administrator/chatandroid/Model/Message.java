package com.example.administrator.chatandroid.Model;

import android.content.Intent;

/**
 * Created by Administrator on 4/14/2016.
 */
public class Message {
    private Integer id;
    private Integer from;
    private Integer to;
    private String text;
    private String date;

    public Message(Integer id, Integer from,Integer to, String text, String date){
        this.id = id;
        this.from = from;
        this.to = to;
        this.text = text;
        this.date = date;
    }
    public Message(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
