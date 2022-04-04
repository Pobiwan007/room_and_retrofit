package com.example.nbabettingapp.service

import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Handler
import android.os.IBinder
import com.example.nbabettingapp.R
import com.example.nbabettingapp.StartActivity
import java.util.*

class MyService: Service() {

    private lateinit var nm: NotificationManager
    val handler: Handler = Handler()
    lateinit var timer: Timer
    lateinit var timerTask: TimerTask

    override fun onCreate() {
        super.onCreate()
        nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.home)
            val descriptionText = getString(R.string.hello_blank_fragment)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            nm.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        timer = Timer()
        timerTask = object: TimerTask() {
            override fun run() {
                handler.post {
                    getPushNotification()
                }
            }
        }
        timer.schedule(timerTask, 0, 5000)
        return START_STICKY
    }


    private fun stopTimerTask() {
        timer.cancel()
    }
    private fun getPushNotification(){
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, "CHANNEL_ID")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, StartActivity::class.java),
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        builder.apply {
            setContentIntent(pendingIntent)
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.form))
            setTicker("NBA wait you")
            setWhen(System.currentTimeMillis())
            setAutoCancel(true)
            setContentTitle("New events in sport")
            setContentText("Click to check!")
        }
        val notification = builder.build()
        nm.notify(234, notification)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        stopTimerTask()
        super.onDestroy()
    }
}