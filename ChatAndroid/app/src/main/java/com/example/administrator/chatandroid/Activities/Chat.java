package com.example.administrator.chatandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.chatandroid.Model.Message;
import com.example.administrator.chatandroid.R;
import com.example.administrator.chatandroid.WebServices.RestGetMessages;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Chat extends AppCompatActivity {
    private int idFrom;
    private int myId;
    private String nameFrom;
    private ArrayList<Message> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent info = getIntent();
        idFrom = info.getIntExtra("FromId", 0);
        myId = info.getIntExtra("MyId", 0);
        nameFrom = info.getStringExtra("FromName");
        TextView textView = (TextView)findViewById(R.id.NombreContacto);
        textView.setText(nameFrom);
        ArrayList<String> mensajes = new ArrayList<String>();
        try {
            messages = new RestGetMessages().execute(idFrom,myId).get();
            if(!messages.isEmpty()) {
                for (Message M : messages) {
                    mensajes.add(M.getText());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.message_template,mensajes);
                ListView lis =(ListView) findViewById(R.id.Messages);
                lis.setAdapter(adapter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
