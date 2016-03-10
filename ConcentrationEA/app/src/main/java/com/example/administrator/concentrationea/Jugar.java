package com.example.administrator.concentrationea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Jugar extends ConcentrationActivity {
    public static final String nJugadores = "nPlayers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void startGame(View view){
        int id = view.getId();
        if (id == R.id.btnp1 ){
            Intent intent = new Intent(this,GetNames.class);
            intent.putExtra(nJugadores,1);
            startActivity(intent);
        }
        else if (id == R.id.btnp2 ){
            Intent intent = new Intent(this,GetNames.class);
            intent.putExtra(nJugadores,2);
            startActivity(intent);
        }
        else if (id == R.id.btnp3 ){
            Intent intent = new Intent(this,GetNames.class);
            intent.putExtra(nJugadores,3);
            startActivity(intent);
        }
        else if (id == R.id.btnp4 ){
            Intent intent = new Intent(this,GetNames.class);
            intent.putExtra(nJugadores,4);
            startActivity(intent);
        }
    }

}
