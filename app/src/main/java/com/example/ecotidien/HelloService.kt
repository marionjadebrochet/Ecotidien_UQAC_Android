package com.example.ecotidien

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

class HelloService : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null


    private inner class ServiceHandler(lopper: Looper): Handler(lopper){
        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException){
                Thread.currentThread().interrupt()
            }

            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        super.onCreate()

        HandlerThread("HelloServiceMainThread", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()

            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "We are starting the command", Toast.LENGTH_LONG).show()

        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "We have finished the job", Toast.LENGTH_LONG).show()
    }
}