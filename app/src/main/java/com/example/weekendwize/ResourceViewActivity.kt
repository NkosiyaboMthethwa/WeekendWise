package com.example.weekendwize

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResourceViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resource_view)
        
        val title = intent.getStringExtra("RESOURCE_TITLE") ?: "Resource"
        val content = intent.getStringExtra("RESOURCE_CONTENT") ?: "No content available."

        findViewById<TextView>(R.id.tvResourceTitle).text = title
        findViewById<TextView>(R.id.tvResourceContent).text = content

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}