package com.aitorzubizarreta.intents.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TelephonyReceiver extends BroadcastReceiver {

    private final String CALL = "CHANGE";

    public TelephonyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(CALL, "TelephonyReceiver onReceive()");
    }
}
