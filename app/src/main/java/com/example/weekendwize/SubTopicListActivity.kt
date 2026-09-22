package com.example.weekendwize

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SubTopicListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_subtopic_list)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.subtopic_list_layout)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val topic = intent.getParcelableExtra<Topic>("TOPIC")

        if (topic != null) {

            findViewById<TextView>(
                R.id.tvSubTopicListTitle
            ).text = topic.name

            val container =
                findViewById<LinearLayout>(
                    R.id.llSubTopicsContainer
                )

            container.removeAllViews()

            if (topic.subTopics.isEmpty()) {

                val noDataView = TextView(this).apply {
                    text =
                        "No subtopics or activities available for this topic yet."

                    setPadding(
                        32,
                        32,
                        32,
                        32
                    )

                    setTextColor(
                        getColor(R.color.black)
                    )
                }

                container.addView(noDataView)

            } else {

                topic.subTopics.forEach { subTopic ->

                    val itemView = LayoutInflater
                        .from(this)
                        .inflate(
                            R.layout.item_topic,
                            container,
                            false
                        )

                    itemView.findViewById<TextView>(
                        R.id.tvTopicName
                    ).text = subTopic.name

                    itemView.setOnClickListener {

                        val intent = Intent(
                            this,
                            QuizActivity::class.java
                        ).apply {
                            putExtra(
                                "QUIZ",
                                subTopic.quiz
                            )
                        }

                        startActivity(intent)
                    }

                    container.addView(itemView)
                }
            }
        }
    }
}