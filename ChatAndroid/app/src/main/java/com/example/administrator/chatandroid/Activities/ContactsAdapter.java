package com.example.administrator.chatandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.chatandroid.Model.Contact;
import com.example.administrator.chatandroid.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 4/14/2016.
 */
public class ContactsAdapter extends ArrayAdapter<Contact> {
    public ContactsAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Contact contact = getItem(position);
        View contactView = LayoutInflater.from(getContext()).inflate(R.layout.contact_template,null,true);
        CustomTextView uName = (CustomTextView) contactView;
        uName.setIdContact(contact.getUserId());
        uName.setContactName(contact.getNombre());
        uName.setText(uName.getContactName());

        return contactView;
    }
}
