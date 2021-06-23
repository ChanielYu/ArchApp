package com.auxy.archapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.auxy.archapp.globalcomponent.ExitEventManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExitService : Service() {
    @Inject
    lateinit var exitEventManager: ExitEventManager

    override fun onBind(intent: Intent): IBinder? = null

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        exitEventManager.removedFromTask()
        stopSelf()
    }
}
