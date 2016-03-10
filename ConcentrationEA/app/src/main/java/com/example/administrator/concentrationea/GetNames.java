package com.example.administrator.concentrationea;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GetNames extends ConcentrationActivity {
    private ArrayList<String> Names;
    private int nPlayers;
    public static final String NombresJ = "Nombres";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_names);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayout lin = (LinearLayout) findViewById(R.id.Laynames);
        Intent intent = getIntent();
        nPlayers = intent.getIntExtra(Jugar.nJugadores, 0);
        for (int i = 0; i < nPlayers;++i){
            TextView nPlayer = new TextView(this);
            EditText namePlayer = new EditText(this);
            nPlayer.setText(getResources().getString(R.string.inNombre) + " " + String.valueOf(i + 1) + " :");
            nPlayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            nPlayer.setTextSize(20);
            namePlayer.setId(i);
            namePlayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lin.addView(nPlayer);
            lin.addView(namePlayer);
        }
        Button ok = new Button(this);
        ok.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ok.setPadding(32, 32, 32, 32);
        ok.setText(R.string.empezar);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Names = new ArrayList<String>();
                String currName;
                for (int i = 0; i < nPlayers; ++i) {
                    EditText namePlayer = (EditText) findViewById(i);
                    currName = namePlayer.getText().toString();
                    if (currName.isEmpty()) {
                        AlertDialog.Builder b = new AlertDialog.Builder(v.getContext());
                        b.setMessage(getResources().getString(R.string.ErrorName));
                        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                        AlertDialog Error = b.create();
                        Error.show();
                        return;
                    }
                    Names.add(currName);
                }
                Intent intent = new Intent(v.getContext(), Juego.class);
                intent.putStringArrayListExtra(NombresJ,Names);
                startActivity(intent);
            }
        });
        lin.addView(ok);
    }

}
