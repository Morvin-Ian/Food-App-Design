package com.example.foodapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private val channelId = "statusChannel1"
    private var notificationManager:NotificationManager?  = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val signIn = findViewById<Button>(R.id.button2)
        val signUp = findViewById<Button>(R.id.button)

        signIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            createNotificationChannel(channelId,"Sign In","Sign in Succesful")
            displayNotification("Sign In","Your Gender is Male/Female stop being Stupid")
        }
        signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            createNotificationChannel(channelId,"Sign Up","Sign Up Succesful")
            displayNotification("Sign Up", "Hurry the F#ck Up")
            startActivity(intent)
        }


    }

    private fun displayNotification(title:String, desc:String){
        val notificationId = 1
        val notification: Notification = NotificationCompat.Builder(this@MainActivity,channelId)
            .setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager?.notify(notificationId,notification)

    }

    private fun createNotificationChannel(id:String,name:String, channelDescription:String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelInstance = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channelInstance)
        }
    }
}