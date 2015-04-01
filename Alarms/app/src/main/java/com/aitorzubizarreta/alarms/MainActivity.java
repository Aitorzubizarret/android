package com.aitorzubizarreta.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private static final String CONSOLE = "Console";
    private Button startBtn;
    private Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startBtn = (Button)findViewById(R.id.startBtn);
        stopBtn = (Button)findViewById(R.id.stopBtn);

        // Creamos el intent que va a ejecutar nuestra función
        Intent intent = new Intent(this, Receiver.class);

        // Necesitamos un PendingIntent
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm(6, pendingIntent);
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm(pendingIntent);
            }
        });
    }
    private void setAlarm(int seconds, PendingIntent pendingIntent) {
        Log.d(CONSOLE, "Alarma");

        // Obtenemos una referencia al gestor de alarmas de Android
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Creamos el tipo de alarma que queremos
        int alarmType = AlarmManager.RTC;

        // Configuramos la alarma
        alarmManager.setRepeating(alarmType, 0, 1000 * seconds, pendingIntent);
    }
    private void stopAlarm(PendingIntent pendingIntent) {
        AlarmManager alarmManagerStop = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManagerStop.cancel(pendingIntent);
    }
}
