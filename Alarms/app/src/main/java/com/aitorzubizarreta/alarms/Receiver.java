package com.aitorzubizarreta.alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cursomovil on 1/04/15.
 */
public class Receiver extends BroadcastReceiver {

    private static final String CONSOLE = "Console";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(CONSOLE, "Alarma ejecutada!");
    }
}
