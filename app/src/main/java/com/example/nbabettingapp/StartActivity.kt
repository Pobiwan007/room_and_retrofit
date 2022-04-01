package com.example.nbabettingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.ldoublem.loadingviewlib.view.LVPlayBall

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<LVPlayBall>(R.id.ball_loader).apply {
            setViewColor(ContextCompat.getColor(this@StartActivity, R.color.green))
            setBallColor(ContextCompat.getColor(this@StartActivity, R.color.orange  ))
            startAnim()
        }

        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}