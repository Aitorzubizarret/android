package com.aitorzubizarreta.intents.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConectionReceiver extends BroadcastReceiver {

    private final String RECEIVER = "CHANGE";

    public ConectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(RECEIVER, "ConnectionReceiver, onReceive()");
        Log.d(RECEIVER, "Action :" + intent.getAction());

        if (intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            // Do something
        } else if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Log.d(RECEIVER, "ELSEIF");
        }
    }
}
