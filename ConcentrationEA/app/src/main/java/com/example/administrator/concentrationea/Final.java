package com.example.administrator.concentrationea;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Final extends ConcentrationActivity {
    private ArrayList<Player> players;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent getInfo = getIntent();
        players = new ArrayList<Player>();
        names = getInfo.getStringArrayListExtra("Nombres");
        generatePlayers(names, getInfo.getIntegerArrayListExtra("Puntajes"));
        TextView winner = (TextView) findViewById(R.id.WinTxt);
        TextView winnerPts = (TextView) findViewById(R.id.WinPts);
        winner.setText(getResources().getString(R.string.Win)+players.get(0).getName());
        winnerPts.setText(String.valueOf(players.get(0).getScore()));
        setTexts(players.get(0).getName());
    }
    public void generatePlayers(ArrayList<String> nombres, ArrayList<Integer> puntajes){
        for (int i = 0; i < nombres.size(); ++i){
            Player exp = new Player(nombres.get(i),puntajes.get(i));
            players.add(exp);
        }
    }
    public void setTexts(String p1){
        LinearLayout lin = (LinearLayout) findViewById(R.id.LOthers);
        for (Player p : players){
            if (p.getName() == p1){
                continue;
            }
            else {
                TextView player = new TextView(this);
                player.setText(p.getName()+"            "+String.valueOf(p.getScore()));
                player.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                player.setTextSize(20);
                lin.addView(player);
            }
        }
    }
    public void again(View view){
        Intent intent = new Intent(this, Juego.class);
        intent.putExtra(GetNames.NombresJ,names);
        startActivity(intent);
    }
    public void newGame(View view){
        Intent intent = new Intent(this, Jugar.class);
        startActivity(intent);
    }

}
