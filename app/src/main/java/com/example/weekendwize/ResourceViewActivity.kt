package com.example.weekendwize

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResourceViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resource_view)

        val title = intent.getStringExtra("RESOURCE_TITLE") ?: getString(R.string.resource_view)
        val content = intent.getStringExtra("RESOURCE_CONTENT") ?: getString(R.string.loading_content)

        findViewById<TextView>(R.id.tvResourceTitle).text = title
        findViewById<TextView>(R.id.tvResourceContent).text = content

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
