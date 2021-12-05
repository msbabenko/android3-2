package com.example.day3task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat

class GreetingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        val number = intent.getStringExtra("userLastNumber")
        val greetingsText = intent.getStringExtra("userGreetingsText")
        val isWon = intent.getBooleanExtra("isWon", true)

        val numberView = findViewById<TextView>(R.id.userLastNumberView)
            numberView.text = number
        if (!isWon)
            numberView.setTextColor(ContextCompat.getColor(this, R.color.red))
        else
            numberView.setTextColor(ContextCompat.getColor(this, R.color.green))
        findViewById<TextView>(R.id.greetingTextView).text = greetingsText

    }
}