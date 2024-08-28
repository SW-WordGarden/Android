package com.sw.wordgarden.presentation.ui.lockscreen

import android.content.Context
import android.content.Intent
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver

class ScreenOffReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_SCREEN_OFF ->{
                val intent = Intent(context, LockScreenActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                context?.startActivity(intent)
            }
        }
    }
}