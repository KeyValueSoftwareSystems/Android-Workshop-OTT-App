package com.keyvalue.workshop.ottworkshopapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.keyvalue.workshop.ottworkshopapp.TAG

class AirplaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED)
        {
            val isTurnedOn = Settings.Global.getInt(context?.contentResolver,Settings.Global.AIRPLANE_MODE_ON) !=0
            Log.d(TAG,"AirplaneMode: $isTurnedOn")
        }
    }
}