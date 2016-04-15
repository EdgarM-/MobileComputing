package com.example.administrator.chatandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.chatandroid.CustomThings.ContactsAdapter;
import com.example.administrator.chatandroid.CustomThings.ContactTextView;
import com.example.administrator.chatandroid.Model.Contact;
import com.example.administrator.chatandroid.R;
import com.example.administrator.chatandroid.WebServices.RestGetContacts;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Contactos extends AppCompatActivity {

    private ArrayList<Contact> contactos;
    private int myId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent getUId = getIntent();
        myId = Integer.parseInt(getUId.getStringExtra("uId"));
        try {
            contactos = new RestGetContacts().execute(myId).get();
            if(!contactos.isEmpty()){
                ArrayAdapter<Contact> adapter = new ContactsAdapter(this,R.layout.contact_template,contactos);
                ListView lis =(ListView) findViewById(R.id.listaContactos);
                lis.setAdapter(adapter);
            }else {
                Toast.makeText(this, "Sad Panda Has no contacts", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void openChat(View v){
        Intent intent = new Intent(this, Chat.class);
        ContactTextView contact = (ContactTextView) v;
        intent.putExtra("FromId",contact.getIdContact());
        intent.putExtra("FromName", contact.getContactName());
        intent.putExtra("MyId",myId);
        startActivity(intent);
    }


}
