package com.example.administrator.concentrationea;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Juego extends ConcentrationActivity {
    private ArrayList<Player> players;
    private ArrayList<String> nombress;
    private ArrayList<Integer> puntajes;
    private int cantPar;
    private int currPlayer;
    private int IdFicha;
    private int IdFicha2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        nombress = new ArrayList<String>();
        puntajes = new ArrayList<Integer>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent getInfo = getIntent();
        ArrayList<String> nombres;
        nombres = getInfo.getStringArrayListExtra(GetNames.NombresJ);
        players = new ArrayList<Player>();
        for (int i=0;i < nombres.size();++i){
            Player noob = new Player(nombres.get(i),0);
            players.add(noob);
        }
        ArrayList<String> Colors = new ArrayList<String>(Arrays.asList("#660000","#FF5500","#FF8000","#FFAA00","#7F9900","#00CC88","#009980","#0F5757","#0F4B57","#8F7AB8","#660000","#FF5500","#FF8000","#FFAA00","#7F9900","#00CC88","#009980","#0F5757","#0F4B57","#8F7AB8"));
        Collections.shuffle(Colors);
        ArrayList<Integer> ids = new ArrayList<Integer>(Arrays.asList(R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,R.id.btn19,R.id.btn20));
        for (int i=0;i < 20;++i){
            Button but=(Button)findViewById(ids.get(i));
            but.setTag(Colors.get(i));
            but.setBackgroundColor(Color.LTGRAY);
        }
        currPlayer = 0;
        IdFicha = 0;
        cantPar = 0;
        ActJugadorPuntaje(players.get(currPlayer).getName(), players.get(currPlayer).getScore());

    }

    public void Actions(View view){
        if (IdFicha == 0){
            IdFicha = view.getId();
            Button but = (Button) findViewById(IdFicha);
            String color = but.getTag().toString();
            but.setBackgroundColor(Color.parseColor(color));
        }
        else{
            if (IdFicha == view.getId()){
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(view.getContext(), getResources().getString(R.string.ErrorClick), duration);
                toast.show();
            }
            else {
                Button but1 = (Button) findViewById(IdFicha);
                Button but2 = (Button) findViewById(view.getId());
                String color = but2.getTag().toString();
                IdFicha2 = view.getId();
                but2.setBackgroundColor(Color.parseColor(color));
                if (but1.getTag().toString().equals(but2.getTag().toString())){
                    but1.setClickable(false);
                    but2.setClickable(false);
                    but2.setBackgroundColor(Color.parseColor(color));
                    players.get(currPlayer).plusScore(1);
                    ActJugadorPuntaje(players.get(currPlayer).getName(), players.get(currPlayer).getScore());
                    cantPar+=1;
                    IdFicha = 0;
                    if (cantPar == 10){
                        Intent intent = new Intent(this,Final.class);
                        generateResults();
                        intent.putExtra("Nombres",nombress);
                        intent.putExtra("Puntajes",puntajes);
                        startActivity(intent);
                    }
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Button but1 = (Button) findViewById(IdFicha);
                            Button but2 = (Button) findViewById(IdFicha2);
                            but1.setBackgroundColor(Color.LTGRAY);
                            but2.setBackgroundColor(Color.LTGRAY);
                            IdFicha = 0;
                        }
                    }, 300);
                    //but1.setBackgroundColor(Color.LTGRAY);
                    //but2.setBackgroundColor(Color.LTGRAY);

                    currPlayer = (currPlayer+1)%players.size();
                    ActJugadorPuntaje(players.get(currPlayer).getName(), players.get(currPlayer).getScore());
                }

            }
        }
    }

    public void ActJugadorPuntaje(String name,int puntaje){
        TextView nombre = (TextView) findViewById(R.id.pPlayer);
        TextView puntos = (TextView) findViewById(R.id.pPoints);
        nombre.setText(getResources().getString(R.string.currP)+name);
        puntos.setText(getResources().getString(R.string.currPPoints)+String.valueOf(puntaje));
    }
    public void generateResults(){
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                return  rhs.getScore() < lhs.getScore() ? -1 : lhs.getScore() == rhs.getScore() ? 0 : 1;
            }
        });
        for (Player p : players){
            nombress.add(p.getName());
            puntajes.add(p.getScore());
        }

    }
}
