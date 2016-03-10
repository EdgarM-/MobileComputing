package com.example.administrator.concentrationea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Acerca extends ConcentrationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void resultAction(View view) {
        Intent returnIntent = new Intent();
        Boolean back = true;
        returnIntent.putExtra("back", back);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }


}
