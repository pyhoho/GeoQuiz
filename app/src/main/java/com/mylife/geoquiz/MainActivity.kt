package com.mylife.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton :Button
    private lateinit var falseButton:Button
    private lateinit var nextButton: Button
    private lateinit var prevButton:Button
    private lateinit var questionTextView: TextView


    private val questionBank = listOf(
        Question(R.string.question_China,true),
        Question(R.string.question_lunch,false),
        Question(R.string.question_school,false),
        Question(R.string.question_work,true),
        Question(R.string.question_tea,false))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener {
//            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_SHORT).show()
                checkAnswer(true)
        }

        falseButton.setOnClickListener {
//            Toast.makeText(this,R.string.incrrect_toast,Toast.LENGTH_SHORT).show()
                checkAnswer(false)
        }
        nextButton.setOnClickListener {
            nextOrPrev()
            updateQuestion()
        }

        prevButton.setOnClickListener {
            nextOrPrev()
            updateQuestion()
        }
        updateQuestion()
        questionTextView.setOnClickListener{
            nextOrPrev()
            updateQuestion()
        }

    }

    private fun nextOrPrev() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incrrect_toast
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
                .show()
    }
}
