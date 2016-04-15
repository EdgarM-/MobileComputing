package com.example.administrator.chatandroid.CustomThings;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.chatandroid.Model.Message;
import com.example.administrator.chatandroid.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 4/15/2016.
 */
public class MessagesAdapter extends ArrayAdapter<Message> {
    private int myId;

    public MessagesAdapter(Context context, int resource, List<Message> objects,int myId) {
        super(context, resource, objects);
        this.myId = myId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Message message = getItem(position);
        View messageView = LayoutInflater.from(getContext()).inflate(R.layout.message_template, null, true);
        TextView messageText = (TextView) messageView;
        messageText.setText(message.getText());
        if(message.getTo() != myId){
            messageText.setBackgroundResource(R.drawable.ovaltext2);
        }else{
            messageText.setBackgroundResource(R.drawable.ovaltext);
        }


        return messageView;
    }
}
