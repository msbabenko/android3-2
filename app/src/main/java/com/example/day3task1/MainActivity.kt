package com.example.day3task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var number = Random.nextInt(1,100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val guessButtonView = findViewById<TextView>(R.id.guessButton)
        val guessNumberView = findViewById<TextInputLayout>(R.id.numberView)
        val hintTextView = findViewById<TextView>(R.id.hintTextView)
        val resetButtonView = findViewById<TextView>(R.id.resetButtom)

        resetButtonView.setOnClickListener {
            finish()
            startActivity(getIntent())
        }

        guessButtonView.setOnClickListener {
            val userNumber = guessNumberView.editText?.text?.toString()?.filter { it.isDigit() }
            var maskedNumber =
                if (userNumber.isNullOrEmpty()) 1111
                else userNumber.toInt()

            guessNumberView.editText?.text?.clear()
            when {
                maskedNumber > 1000 -> hintTextView.setText("Please, enter a number [0-1000]")
                maskedNumber < number -> hintTextView.setText("Not $maskedNumber :) My number is bigger")
                maskedNumber > number  -> hintTextView.setText("No $maskedNumber :) My number is smaller")
                else     -> {
                    hintTextView.setText("You are right! it's $maskedNumber")
                    guessButtonView.isEnabled = false
                }


            }
        }

    }
}