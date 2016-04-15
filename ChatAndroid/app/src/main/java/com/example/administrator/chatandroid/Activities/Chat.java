package com.example.administrator.chatandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.chatandroid.CustomThings.MessagesAdapter;
import com.example.administrator.chatandroid.LocalStorage.MessageStorage;
import com.example.administrator.chatandroid.Model.Message;
import com.example.administrator.chatandroid.R;
import com.example.administrator.chatandroid.WebServices.RestGetMessages;
import com.example.administrator.chatandroid.WebServices.RestPostMessage;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Chat extends AppCompatActivity {
    private int idFrom;
    private int myId;
    private String nameFrom;
    private ArrayList<Message> messages;
    private MessageStorage storedMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        storedMessages = new MessageStorage(this);
        Intent info = getIntent();
        idFrom = info.getIntExtra("FromId", 0);
        myId = info.getIntExtra("MyId", 0);
        nameFrom = info.getStringExtra("FromName");
        TextView textView = (TextView)findViewById(R.id.NombreContacto);
        textView.setText(nameFrom);
        ArrayList<String> mensajes = new ArrayList<String>();
        messages = storedMessages.getUserMessages(idFrom,myId);
        messages.addAll(storedMessages.getUserMessages(myId,idFrom));
        try {
            ArrayList<Message> restMessages =new RestGetMessages().execute(idFrom, myId).get();
            messages.addAll(restMessages);
            storeNewMessages(restMessages);
            if(!messages.isEmpty()) {
                MessagesAdapter adapter = new MessagesAdapter(this,R.layout.message_template,messages,myId);
                ListView lis =(ListView) findViewById(R.id.Messages);
                lis.setAdapter(adapter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public void refreshChat(View v){

        try {
            ArrayList<Message> newMessages = new RestGetMessages().execute(idFrom,myId).get();
            storeNewMessages(newMessages);
            messages.addAll(newMessages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        refreshWindow();
    }

    public void storeNewMessages(ArrayList<Message> newMessages){
        for (Message message : newMessages){
            storedMessages.insertMessage(message.getFrom(),message.getTo(),message.getText(),message.getDate());
        }
    }

    public void sendMessage(View v){
        EditText editText = (EditText) findViewById(R.id.Message);
        try {
            String text = editText.getText().toString();
            Message message = new RestPostMessage().execute(String.valueOf(myId), String.valueOf(idFrom),text).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        editText.setText("");
        refreshWindow();
    }

    private void refreshWindow(){
        if(!messages.isEmpty()) {
            MessagesAdapter adapter = new MessagesAdapter(this,R.layout.message_template,messages,myId);
            ListView lis =(ListView) findViewById(R.id.Messages);
            lis.setAdapter(adapter);
        }
    }

}
