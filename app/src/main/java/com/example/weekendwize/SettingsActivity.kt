package com.example.weekendwize

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class SettingsActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        val isGuest = intent.getBooleanExtra("IS_GUEST", false)

        val appPrefs = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val isDarkMode = appPrefs.getBoolean("DARK_MODE", false)

        val switchDarkMode = findViewById<SwitchMaterial>(R.id.switchDarkMode)
        switchDarkMode.isChecked = isDarkMode

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            appPrefs.edit().putBoolean("DARK_MODE", isChecked).apply()
        }

        // Account Details
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
        } else {
            val userPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            etUsername.setText(userPrefs.getString("FULL_NAME", "User"))
            etEmail.setText(userPrefs.getString("EMAIL", "user@example.com"))
        }

        btnUpdate.setOnClickListener {
            if (!isGuest) {
                val newUsername = etUsername.text.toString().trim()
                val newEmail = etEmail.text.toString().trim()
                val newPassword = etPassword.text.toString().trim()

                val userPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = userPrefs.edit()

                if (newUsername.isNotEmpty()) editor.putString("FULL_NAME", newUsername)
                if (newEmail.isNotEmpty()) editor.putString("EMAIL", newEmail)
                if (newPassword.isNotEmpty()) {
                    val encrypted = SecurityUtils.hashPassword(newPassword)
                    editor.putString("PASSWORD", encrypted)
                }
                editor.apply()

                Toast.makeText(this, "Account updated & encrypted successfully!", Toast.LENGTH_SHORT).show()
                etPassword.text?.clear()
            }
        }

        // Multi-Language Support (English, isiZulu, Afrikaans)
        val rgLanguage = findViewById<RadioGroup>(R.id.rgLanguage)
        val currentLang = LocaleHelper.getLanguage(this)

        when (currentLang) {
            "zu" -> findViewById<RadioButton>(R.id.rbZulu).isChecked = true
            "af" -> findViewById<RadioButton>(R.id.rbAfrikaans).isChecked = true
            else -> findViewById<RadioButton>(R.id.rbEnglish).isChecked = true
        }

        rgLanguage.setOnCheckedChangeListener { _, checkedId ->
            val selectedLang = when (checkedId) {
                R.id.rbZulu -> "zu"
                R.id.rbAfrikaans -> "af"
                else -> "en"
            }

            if (selectedLang != LocaleHelper.getLanguage(this)) {
                LocaleHelper.setLocale(this, selectedLang)
                Toast.makeText(this, "Language updated!", Toast.LENGTH_SHORT).show()

                // Restart app to apply language change across activities
                val intent = Intent(this, HomeActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("IS_GUEST", isGuest)
                }
                startActivity(intent)
                finish()
            }
        }

        // Offline Sync Status & Manual Trigger
        val dbHelper = OfflineDatabaseHelper(this)
        val tvSyncStatus = findViewById<TextView>(R.id.tvSyncStatus)
        val pendingCount = dbHelper.getUnsyncedCount()
        tvSyncStatus.text = "Offline Actions Pending: $pendingCount"

        val btnSyncNow = findViewById<Button>(R.id.btnSyncNow)
        btnSyncNow.setOnClickListener {
            if (NetworkMonitor.isOnline(this)) {
                btnSyncNow.isEnabled = false
                btnSyncNow.text = "Syncing..."
                OfflineSyncManager.performSync(this) { syncedCount ->
                    btnSyncNow.isEnabled = true
                    btnSyncNow.text = getString(R.string.sync_now)
                    val remaining = dbHelper.getUnsyncedCount()
                    tvSyncStatus.text = "Offline Actions Pending: $remaining"
                    Toast.makeText(this, "Synced $syncedCount item(s)!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Device is offline. Connect to network to sync.", Toast.LENGTH_SHORT).show()
            }
        }

        // Real-time Notifications Switch
        val switchNotifications = findViewById<SwitchMaterial>(R.id.switchNotifications)
        switchNotifications.isChecked = appPrefs.getBoolean("NOTIFICATIONS_ENABLED", true)
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            appPrefs.edit().putBoolean("NOTIFICATIONS_ENABLED", isChecked).apply()
            if (isChecked) {
                NotificationHelper.showNotification(this, "Notifications Enabled", "You will receive real-time study updates and sync alerts.")
            }
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
