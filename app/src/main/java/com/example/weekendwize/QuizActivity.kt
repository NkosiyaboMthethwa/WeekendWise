package com.example.weekendwize

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : BaseActivity() {

    private var currentQuestionIndex = 0
    private lateinit var quiz: Quiz
    private var subjectName: String = "General"
    private var subTopicName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quiz_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        quiz = intent.getParcelableExtra("QUIZ") ?: Quiz(emptyList())
        subjectName = intent.getStringExtra("SUBJECT_NAME") ?: "General"
        subTopicName = intent.getStringExtra("SUBTOPIC_NAME") ?: ""

        if (quiz.questions.isEmpty()) {
            Toast.makeText(this, getString(R.string.no_data_subtopics), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        displayQuestion()

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
            val checkedId = rgOptions.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, getString(R.string.please_select_answer), Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, getString(R.string.correct_toast), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.incorrect_toast), Toast.LENGTH_SHORT).show()
            }

            if (currentQuestionIndex < quiz.questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            } else {
                markQuizCompleted()
                Toast.makeText(this, getString(R.string.quiz_completed_toast), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun markQuizCompleted() {
        val prefs = getSharedPreferences("AppProgress", Context.MODE_PRIVATE)
        val key = "QUIZ_COMPLETED_${subjectName}_${subTopicName}"
        if (!prefs.getBoolean(key, false)) {
            prefs.edit().putBoolean(key, true).apply()

            val countKey = "QUIZ_COUNT_${subjectName}"
            val currentCount = prefs.getInt(countKey, 0)
            prefs.edit().putInt(countKey, currentCount + 1).apply()
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
