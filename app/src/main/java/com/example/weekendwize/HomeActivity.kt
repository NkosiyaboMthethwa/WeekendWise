package com.example.weekendwize

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    
    private val selectedSessions = mutableSetOf<Int>()
    private var isGuest = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userName = intent.getStringExtra("USER_NAME") ?: "User"
        isGuest = intent.getBooleanExtra("IS_GUEST", false)
        findViewById<TextView>(R.id.tvGreeting).text = "Hi, $userName 👋"

        val clHomeView = findViewById<View>(R.id.clHomeView)
        val svRevisionView = findViewById<View>(R.id.svRevisionView)
        val svBookingsView = findViewById<View>(R.id.svBookingsView)
        val svProfileView = findViewById<View>(R.id.svProfileView)
        val tvHomeTitle = findViewById<TextView>(R.id.tvHomeTitle)

        val navHome = findViewById<TextView>(R.id.navHome)
        val navRevision = findViewById<TextView>(R.id.navRevision)
        val navBookings = findViewById<TextView>(R.id.navBookings)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        val tvProfileInitials = findViewById<TextView>(R.id.tvProfileInitials)
        val tvProfileNameGrade = findViewById<TextView>(R.id.tvProfileNameGrade)

        val initials = if (userName.isNotEmpty()) {
            val parts = userName.split(" ")
            if (parts.size > 1) {
                "${parts[0].take(1)}${parts[1].take(1)}".uppercase()
            } else {
                userName.take(2).uppercase()
            }
        } else {
            "U"
        }
        tvProfileInitials.text = initials
        tvProfileNameGrade.text = "$userName · Grade 11"

        val pbMaths = findViewById<ProgressBar>(R.id.pbMaths)
        val pbLifeSciences = findViewById<ProgressBar>(R.id.pbLifeSciences)
        val pbPhysicalSci = findViewById<ProgressBar>(R.id.pbPhysicalSci)
        val pbAccounting = findViewById<ProgressBar>(R.id.pbAccounting)
        val tvProgress = findViewById<TextView>(R.id.tvProgress)

        // Progress restriction for Guest
        if (isGuest) {
            tvProgress.text = "Progress tracking unavailable for Guest"
            findViewById<View>(R.id.pbMaths).parent.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbLifeSciences).parent.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbPhysicalSci).parent.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbAccounting).parent.let { (it as View).visibility = View.GONE }
        } else {
            pbMaths.progress = 68
            pbLifeSciences.progress = 52
            pbPhysicalSci.progress = 42
            pbAccounting.progress = 58
            val overallAverage = (68 + 52 + 42 + 58) / 4
            tvProgress.text = "Progress this week: $overallAverage%"
        }

        fun resetNavStyles() {
            navHome.setTypeface(null, Typeface.NORMAL)
            navRevision.setTypeface(null, Typeface.NORMAL)
            navBookings.setTypeface(null, Typeface.NORMAL)
            navProfile.setTypeface(null, Typeface.NORMAL)
        }

        navHome.setOnClickListener {
            clHomeView.visibility = View.VISIBLE
            svRevisionView.visibility = View.GONE
            svBookingsView.visibility = View.GONE
            svProfileView.visibility = View.GONE
            tvHomeTitle.text = "Home"
            resetNavStyles()
            navHome.setTypeface(null, Typeface.BOLD)
        }

        navRevision.setOnClickListener {
            clHomeView.visibility = View.GONE
            svRevisionView.visibility = View.VISIBLE
            svBookingsView.visibility = View.GONE
            svProfileView.visibility = View.GONE
            tvHomeTitle.text = "Revision & Notes"
            resetNavStyles()
            navRevision.setTypeface(null, Typeface.BOLD)
        }

        navBookings.setOnClickListener {
            clHomeView.visibility = View.GONE
            svRevisionView.visibility = View.GONE
            svBookingsView.visibility = View.VISIBLE
            svProfileView.visibility = View.GONE
            tvHomeTitle.text = "Weekend Timetable"
            resetNavStyles()
            navBookings.setTypeface(null, Typeface.BOLD)
        }

        navProfile.setOnClickListener {
            clHomeView.visibility = View.GONE
            svRevisionView.visibility = View.GONE
            svBookingsView.visibility = View.GONE
            svProfileView.visibility = View.VISIBLE
            tvHomeTitle.text = "My Progress"
            resetNavStyles()
            navProfile.setTypeface(null, Typeface.BOLD)
        }

        // Settings Button
        findViewById<ImageButton>(R.id.btnSettings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java).apply {
                putExtra("IS_GUEST", isGuest)
            }
            startActivity(intent)
        }

        // --- Bookings Logic ---
        val sessionViews = listOf(
            R.id.llSatMath to "Sat 08:00 - Maths",
            R.id.llSatPhysics to "Sat 10:15 - Physics",
            R.id.llSunMath to "Sun 08:00 - Maths",
            R.id.llSunPhysics to "Sun 10:15 - Physics"
        )

        sessionViews.forEach { (id, name) ->
            val view = findViewById<View>(id)
            view.setOnClickListener {
                if (isGuest) {
                    Toast.makeText(this, "Bookings are unavailable for Guest", Toast.LENGTH_SHORT).show()
                } else {
                    if (selectedSessions.contains(id)) {
                        selectedSessions.remove(id)
                        view.setBackgroundResource(R.drawable.edit_text_border)
                    } else {
                        selectedSessions.add(id)
                        view.setBackgroundColor(getColor(R.color.weekend_box_border))
                    }
                }
            }
        }

        findViewById<Button>(R.id.btnBookSession).setOnClickListener {
            if (isGuest) {
                Toast.makeText(this, "Bookings are unavailable for Guest", Toast.LENGTH_SHORT).show()
            } else if (selectedSessions.isEmpty()) {
                Toast.makeText(this, "Please select at least one session", Toast.LENGTH_SHORT).show()
            } else {
                val bookedNames = sessionViews.filter { selectedSessions.contains(it.first) }.map { it.second }
                val displayText = bookedNames.joinToString("\n")
                findViewById<TextView>(R.id.tvWeekendDetails).text = displayText
                
                addToHistory("Booked sessions: ${bookedNames.joinToString(", ")}")
                Toast.makeText(this, "Sessions Booked Successfully!", Toast.LENGTH_SHORT).show()
                navHome.performClick()
            }
        }

        // --- Revision Resources ---
        val subjects = DataRepository.subjects
        val math = subjects.find { it.name == "Mathematics" }
        val physics = subjects.find { it.name == "Physical Sciences" }
        val life = subjects.find { it.name == "Life Sciences" }
        val acc = subjects.find { it.name == "Accounting" }

        fun openResource(title: String, content: String) {
            val intent = Intent(this, ResourceViewActivity::class.java).apply {
                putExtra("RESOURCE_TITLE", title)
                putExtra("RESOURCE_CONTENT", content)
            }
            startActivity(intent)
            addToHistory("Viewed resource: $title")
        }

        findViewById<Button>(R.id.btnMathTheory).setOnClickListener { openResource("Math Theory", math?.theory ?: "") }
        findViewById<Button>(R.id.btnMathNotes).setOnClickListener { openResource("Math Notes", math?.notes ?: "") }
        findViewById<Button>(R.id.btnMathRevision).setOnClickListener { openResource("Math Revision", math?.revision ?: "") }

        findViewById<Button>(R.id.btnSciTheory).setOnClickListener { openResource("Physics Theory", physics?.theory ?: "") }
        findViewById<Button>(R.id.btnSciNotes).setOnClickListener { openResource("Physics Notes", physics?.notes ?: "") }
        findViewById<Button>(R.id.btnSciRevision).setOnClickListener { openResource("Physics Revision", physics?.revision ?: "") }

        findViewById<Button>(R.id.btnLifeTheory).setOnClickListener { openResource("Life Science Theory", life?.theory ?: "") }
        findViewById<Button>(R.id.btnLifeNotes).setOnClickListener { openResource("Life Science Notes", life?.notes ?: "") }
        findViewById<Button>(R.id.btnLifeRevision).setOnClickListener { openResource("Life Science Revision", life?.revision ?: "") }

        findViewById<Button>(R.id.btnAccTheory).setOnClickListener { openResource("Accounting Theory", acc?.theory ?: "") }
        findViewById<Button>(R.id.btnAccNotes).setOnClickListener { openResource("Accounting Notes", acc?.notes ?: "") }
        findViewById<Button>(R.id.btnAccRevision).setOnClickListener { openResource("Accounting Revision", acc?.revision ?: "") }

        // Home Page Quizzes
        findViewById<Button>(R.id.btnMaths).setOnClickListener {
            val intent = Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 0) }
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnLifeSciences).setOnClickListener {
            val intent = Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 2) }
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnPhysicalSci).setOnClickListener {
            val intent = Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 1) }
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnAccounting).setOnClickListener {
            val intent = Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 3) }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnBadgesStreaks).setOnClickListener {
            Toast.makeText(this, "Complete quizzes to earn badges!", Toast.LENGTH_LONG).show()
        }
    }

    private fun addToHistory(action: String) {
        if (isGuest) return // Don't track history for Guest
        val sharedPrefs = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val history = sharedPrefs.getString("ACTIVITY_HISTORY", "") ?: ""
        val newHistory = "• $action - Just now\n$history"
        sharedPrefs.edit().putString("ACTIVITY_HISTORY", newHistory.take(500)).apply()
    }
}