package com.example.fatouaicha.appliandroid2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //on crée une classe timer de type TImer
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on instancie la classe timer
        timer = new Timer();
        //on utilise la fonction schedule qui permet de changer d'activité aprés un nombre de secondes
        timer.schedule(new TimerTask() { // la classe timerTask permet de faire des actions, evenement aprés un certain temps
            @Override
            public void run() {
                // on fait la transition d'activités, la classe intent permet de faire ce changement
                Intent intent = new Intent(MainActivity.this, DisparitionBoutton.class);
                // on commence l'activité de changement d'activité
                startActivity(intent);
                finish();
            }
        },5000);
    }
}
