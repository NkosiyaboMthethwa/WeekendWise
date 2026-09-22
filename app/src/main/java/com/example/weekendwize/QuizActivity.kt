package com.example.weekendwize

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quiz_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        quiz = intent.getParcelableExtra("QUIZ") ?: Quiz(
            listOf(Question("What is 2x + 3x?", listOf("4x", "5x", "6x", "7x"), 1))
        )

        displayQuestion()

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
            val checkedId = rgOptions.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedIndex = when (checkedId) {
                R.id.rbOption1 -> 0
                R.id.rbOption2 -> 1
                R.id.rbOption3 -> 2
                R.id.rbOption4 -> 3
                else -> -1
            }

            val question = quiz.questions[currentQuestionIndex]
            if (selectedIndex == question.correctOptionIndex) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect answer.", Toast.LENGTH_SHORT).show()
            }

            if (currentQuestionIndex < quiz.questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            } else {
                Toast.makeText(this, "Quiz completed!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun displayQuestion() {
        val question = quiz.questions[currentQuestionIndex]
        findViewById<TextView>(R.id.tvQuestion).text = question.text
        findViewById<RadioGroup>(R.id.rgOptions).clearCheck()

        findViewById<RadioButton>(R.id.rbOption1).text = question.options.getOrNull(0) ?: ""
        findViewById<RadioButton>(R.id.rbOption2).text = question.options.getOrNull(1) ?: ""
        findViewById<RadioButton>(R.id.rbOption3).text = question.options.getOrNull(2) ?: ""
        findViewById<RadioButton>(R.id.rbOption4).text = question.options.getOrNull(3) ?: ""
    }
}