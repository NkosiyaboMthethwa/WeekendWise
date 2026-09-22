package com.example.weekendwize

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        val isGuest = intent.getBooleanExtra("IS_GUEST", false)

        val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)

        val switchDarkMode = findViewById<SwitchMaterial>(R.id.switchDarkMode)
        switchDarkMode.isChecked = isDarkMode

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            sharedPreferences.edit().putBoolean("DARK_MODE", isChecked).apply()
        }

        val etUsername = findViewById<TextInputEditText>(R.id.etSettingsUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.etSettingsEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etSettingsPassword)
        val btnUpdate = findViewById<Button>(R.id.btnUpdateAccount)

        if (isGuest) {
            etUsername.isEnabled = false
            etEmail.isEnabled = false
            etPassword.isEnabled = false
            btnUpdate.isEnabled = false
            btnUpdate.text = "Unavailable for Guest"
            
            // Optionally hide the history if Guest shouldn't see it (though requirements didn't specify)
            // findViewById<View>(R.id.llHistoryContainer).visibility = View.GONE
        } else {
            val userPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            etUsername.setText(userPrefs.getString("FULL_NAME", "User"))
            etEmail.setText(userPrefs.getString("EMAIL", "user@example.com"))
        }

        btnUpdate.setOnClickListener {
            if (!isGuest) {
                val newUsername = etUsername.text.toString()
                val userPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                userPrefs.edit().putString("FULL_NAME", newUsername).apply()
                Toast.makeText(this, "Account updated successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}