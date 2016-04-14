package com.example.administrator.chatandroid.Activities;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.administrator.chatandroid.R;

/**
 * Created by Administrator on 4/14/2016.
 */
public class CustomTextView extends TextView {
    private int idContact;
    private String contactName;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
