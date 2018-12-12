package com.example.yassinekarami.smssend;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

import android.telephony.SmsManager;

import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.Manifest;

public class MainActivity extends AppCompatActivity {



    private TextView textView;
    private String message = "bonjour NoNo :) ";
    private SmsManager smsManager;
    private Button sendBtn;

    private final static String numero = "0650664099";

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.text_view);
        sendBtn = (Button) findViewById(R.id.btnSend);

        textView.setText(numero);

        if (checkPermission(Manifest.permission.SEND_SMS))
        {
            sendBtn.setEnabled(true);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    public void SendMessageClick(View view){

        // on récupère le message a envoyé

        smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(numero, null, message, null, null);
    }

    private boolean checkPermission(String permission)
    {
        int ok = ContextCompat.checkSelfPermission(this,permission);
        if (ok ==  PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }





}
