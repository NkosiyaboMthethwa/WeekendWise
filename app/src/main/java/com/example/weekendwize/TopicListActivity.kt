package com.example.weekendwize

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TopicListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_topic_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.topic_list_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val subjectIndex = intent.getIntExtra("SUBJECT_INDEX", 0)
        val subject = DataRepository.subjects.getOrNull(subjectIndex)

        if (subject != null) {
            findViewById<TextView>(R.id.tvTopicListTitle).text = "${subject.name} - ${subject.grade}"

            val container = findViewById<LinearLayout>(R.id.llTopicsContainer)
            container.removeAllViews()

            subject.topics.forEach { topic ->
                val itemView = LayoutInflater.from(this).inflate(R.layout.item_topic, container, false)
                itemView.findViewById<TextView>(R.id.tvTopicName).text = topic.name
                
                itemView.setOnClickListener {
                    val intent = Intent(this, SubTopicListActivity::class.java).apply {
                        putExtra("TOPIC", topic)
                    }
                    startActivity(intent)
                }
                container.addView(itemView)
            }
        }
    }
}