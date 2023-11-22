package com.keyvalue.workshop.ottworkshopapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.keyvalue.workshop.ottworkshopapp.TAG

class TestReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "TEST_ACTION")
        {
            Log.d(TAG,"Test received")
        }
    }
}