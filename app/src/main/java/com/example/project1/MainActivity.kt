package com.example.project1


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var word = FourLetterWordList.getRandomFourLetterWord()
        val button = findViewById<Button>(R.id.guess)
        val reset = findViewById<Button>(R.id.reset)
        val input = findViewById<EditText>(R.id.edittext)
        var strValue = ""


        val answer = findViewById<TextView>(R.id.textView22)
        answer.text = word

        val guess1 = findViewById<TextView>(R.id.textView15)
        val guess1check = findViewById<TextView>(R.id.textView16)
        val guess2 = findViewById<TextView>(R.id.textView17)
        val guess2check = findViewById<TextView>(R.id.textView18)
        val guess3 = findViewById<TextView>(R.id.textView19)
        val guess3check = findViewById<TextView>(R.id.textView21)

        var counter = 0
        button.setOnClickListener {
            strValue = input.text.toString().uppercase()
            input.getText().clear()
            closeKeyboard(input)
            counter++

            if (counter == 1) {
                guess1.text = strValue
                Log.d(null, strValue)
                guess1check.text = checkGuess(strValue, word)
            }
            if (counter == 2) {
                guess2.text = strValue
                guess2check.text = checkGuess(strValue, word)
            }
            if (counter == 3) {
                guess3.text = strValue
                guess3check.text = checkGuess(strValue, word)
                Log.d(null, counter.toString())
                answer.visibility = View.VISIBLE
                reset.visibility = View.VISIBLE
                reset.setOnClickListener {
                    guess1.text = "- - - -"
                    guess1check.text = "- - - -"
                    guess2.text = "- - - -"
                    guess2check.text = "- - - -"
                    guess3.text = "- - - -"
                    guess3check.text = "- - - -"
                    word = FourLetterWordList.getRandomFourLetterWord()
                    answer.text = word
                    answer.visibility = View.INVISIBLE
                    reset.visibility = View.INVISIBLE
                }
            }
        }



    }

    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}