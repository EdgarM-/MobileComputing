package com.example.administrator.concentrationea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ConcentrationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void about(View view)
    {
        Intent intent = new Intent(this,Acerca.class);
        startActivityForResult(intent,1);
    }
    public void play(View view)
    {
        Intent intent = new Intent(this,Jugar.class);
        startActivity(intent);
        //startActivityForResult(intent,1);
    }

}
