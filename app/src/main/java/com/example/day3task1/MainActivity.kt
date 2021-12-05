package com.example.day3task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var number = Random.nextInt(1,1000)
    var attempt = 1

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
                maskedNumber > 1000 -> hintTextView.setText("Attempt $attempt \nPlease, enter a number [0-1000]")
                maskedNumber < number -> {
                    if (attempt == 12) gameOver(
                        number, "You lost after 12 attempts!\nThe number is", false
                    ) else
                    hintTextView.setText("Attempt $attempt \nNot $maskedNumber :) My number is bigger")
                    attempt++
                }
                maskedNumber > number  -> {
                    if (attempt == 12) gameOver(
                        number, "You lost after 12 attempts!\nThe number is", false
                    ) else
                    hintTextView.setText("Attempt $attempt \nNo $maskedNumber :) My number is smaller")
                    attempt++
                }
                else     -> {
                    gameOver(
                        maskedNumber, "You won! The number is", true
                    )
                }
            }
        }
    }
    private fun gameOver(
        userLastNumber: Int,
        userGreetingsText: String,
        isWon: Boolean
    ){
        val newScreenIntent = Intent (this, GreetingActivity::class.java)
        newScreenIntent.putExtra("userLastNumber", "$userLastNumber")
        newScreenIntent.putExtra("userGreetingsText", userGreetingsText)
        newScreenIntent.putExtra("isWon", isWon)
        startActivity(newScreenIntent)
    }
}