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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private val selectedSessions = mutableSetOf<Int>()
    private var isGuest = false

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Notification Channel
        NotificationHelper.createNotificationChannel(this)

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

        // Fetch Daily Study Tip using REST API (Retrofit)
        fetchDailyTip()

        // Update Dynamic Progress Bars
        updateProgressBars()

        // Register Network Monitor for Automatic Offline Sync upon reconnection
        NetworkMonitor.registerNetworkCallback(this) {
            OfflineSyncManager.performSync(this)
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
            tvHomeTitle.text = getString(R.string.home_tab)
            resetNavStyles()
            navHome.setTypeface(null, Typeface.BOLD)
            updateProgressBars()
        }

        navRevision.setOnClickListener {
            clHomeView.visibility = View.GONE
            svRevisionView.visibility = View.VISIBLE
            svBookingsView.visibility = View.GONE
            svProfileView.visibility = View.GONE
            tvHomeTitle.text = getString(R.string.revision_tab)
            resetNavStyles()
            navRevision.setTypeface(null, Typeface.BOLD)
        }

        navBookings.setOnClickListener {
            clHomeView.visibility = View.GONE
            svRevisionView.visibility = View.GONE
            svBookingsView.visibility = View.VISIBLE
            svProfileView.visibility = View.GONE
            tvHomeTitle.text = getString(R.string.bookings_tab)
            resetNavStyles()
            navBookings.setTypeface(null, Typeface.BOLD)
        }

        navProfile.setOnClickListener {
            clHomeView.visibility = View.GONE
            svRevisionView.visibility = View.GONE
            svBookingsView.visibility = View.GONE
            svProfileView.visibility = View.VISIBLE
            tvHomeTitle.text = getString(R.string.progress_tab)
            resetNavStyles()
            navProfile.setTypeface(null, Typeface.BOLD)
            updateProgressBars()
        }

        // Working Notifications Icon Button
        findViewById<ImageButton>(R.id.btnNotifications).setOnClickListener {
            showNotificationsDialog()
        }

        // Settings Icon Button
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

        sessionViews.forEach { (id, _) ->
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

                val actionMsg = "Booked sessions: ${bookedNames.joinToString(", ")}"
                addToHistory(actionMsg)

                // Save to local SQLite database for offline actions
                val dbHelper = OfflineDatabaseHelper(this)
                dbHelper.addOfflineAction("user_123", "BOOK_SESSION", actionMsg)

                // Trigger Sync if online, otherwise queue offline
                if (NetworkMonitor.isOnline(this)) {
                    OfflineSyncManager.performSync(this)
                } else {
                    Toast.makeText(this, "Action saved offline. Will sync when online.", Toast.LENGTH_LONG).show()
                }

                NotificationHelper.showNotification(
                    this,
                    "Session Booked!",
                    "Your weekend study session has been booked successfully."
                )

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
        val bus = subjects.find { it.name == "Business Studies" }
        val geo = subjects.find { it.name == "Geography" }

        fun openResource(title: String, content: String) {
            val intent = Intent(this, ResourceViewActivity::class.java).apply {
                putExtra("RESOURCE_TITLE", title)
                putExtra("RESOURCE_CONTENT", content)
            }
            startActivity(intent)
            if (!isGuest) {
                val prefs = getSharedPreferences("AppProgress", Context.MODE_PRIVATE)
                prefs.edit().putBoolean("RESOURCE_VIEWED_${title}", true).apply()
                addToHistory("Viewed resource: $title")
            }
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

        findViewById<Button>(R.id.btnBusTheory).setOnClickListener { openResource("Business Theory", bus?.theory ?: "") }
        findViewById<Button>(R.id.btnBusNotes).setOnClickListener { openResource("Business Notes", bus?.notes ?: "") }
        findViewById<Button>(R.id.btnBusRevision).setOnClickListener { openResource("Business Revision", bus?.revision ?: "") }

        findViewById<Button>(R.id.btnGeoTheory).setOnClickListener { openResource("Geography Theory", geo?.theory ?: "") }
        findViewById<Button>(R.id.btnGeoNotes).setOnClickListener { openResource("Geography Notes", geo?.notes ?: "") }
        findViewById<Button>(R.id.btnGeoRevision).setOnClickListener { openResource("Geography Revision", geo?.revision ?: "") }

        // Home Page Quizzes (Mapping by subject index in DataRepository.subjects)
        findViewById<Button>(R.id.btnMaths).setOnClickListener {
            startActivity(Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 0) })
        }
        findViewById<Button>(R.id.btnPhysicalSci).setOnClickListener {
            startActivity(Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 1) })
        }
        findViewById<Button>(R.id.btnLifeSciences).setOnClickListener {
            startActivity(Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 2) })
        }
        findViewById<Button>(R.id.btnAccounting).setOnClickListener {
            startActivity(Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 3) })
        }
        findViewById<Button>(R.id.btnBusiness).setOnClickListener {
            startActivity(Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 4) })
        }
        findViewById<Button>(R.id.btnGeography).setOnClickListener {
            startActivity(Intent(this, TopicListActivity::class.java).apply { putExtra("SUBJECT_INDEX", 5) })
        }

        findViewById<Button>(R.id.btnBadgesStreaks).setOnClickListener {
            Toast.makeText(this, "Complete quizzes to earn badges!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        updateProgressBars()
    }

    private fun showNotificationsDialog() {
        val notifications = arrayOf(
            "🔔 Weekend Study Reminder\nMaths & Physics revision starts Sat 08:00 AM. (Just now)",
            "📚 New Resource Added\nGrade 11 Accounting & Life Sciences notes available. (2h ago)",
            "🏆 Weekly Progress Alert\nYou completed 75% of your weekly study goals! (1d ago)",
            "🔄 Offline Sync Status\nAll offline study actions synced to the cloud. (2d ago)"
        )

        AlertDialog.Builder(this)
            .setTitle("Notifications 🔔")
            .setItems(notifications) { _, index ->
                val selectedTitle = notifications[index].substringBefore("\n")
                Toast.makeText(this, "Opened: $selectedTitle", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Test Push") { _, _ ->
                NotificationHelper.showNotification(
                    this,
                    "Study Reminder 📚",
                    "Don't forget to practice Grade 11 Mathematics past papers today!"
                )
                Toast.makeText(this, "Test notification triggered!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Clear All") { _, _ ->
                Toast.makeText(this, "Notifications cleared", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Close", null)
            .show()
    }

    private fun updateProgressBars() {
        val pbMaths = findViewById<ProgressBar>(R.id.pbMaths)
        val pbLifeSciences = findViewById<ProgressBar>(R.id.pbLifeSciences)
        val pbPhysicalSci = findViewById<ProgressBar>(R.id.pbPhysicalSci)
        val pbAccounting = findViewById<ProgressBar>(R.id.pbAccounting)
        val pbBusiness = findViewById<ProgressBar>(R.id.pbBusiness)
        val pbGeography = findViewById<ProgressBar>(R.id.pbGeography)
        val tvProgress = findViewById<TextView>(R.id.tvProgress)

        if (isGuest) {
            tvProgress.text = "Progress tracking unavailable for Guest"
            findViewById<View>(R.id.pbMaths)?.parent?.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbLifeSciences)?.parent?.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbPhysicalSci)?.parent?.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbAccounting)?.parent?.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbBusiness)?.parent?.let { (it as View).visibility = View.GONE }
            findViewById<View>(R.id.pbGeography)?.parent?.let { (it as View).visibility = View.GONE }
            return
        }

        val prefs = getSharedPreferences("AppProgress", Context.MODE_PRIVATE)

        fun calculateSubjectProgress(subjectName: String, quizCount: Int, resourceTitles: List<String>): Int {
            var completedCount = 0
            val completedQuizzes = prefs.getInt("QUIZ_COUNT_${subjectName}", 0)
            completedCount += completedQuizzes

            resourceTitles.forEach { title ->
                if (prefs.getBoolean("RESOURCE_VIEWED_${title}", false)) completedCount++
            }

            val total = quizCount + resourceTitles.size
            return if (total > 0) (completedCount * 100) / total else 0
        }

        val mathProg = calculateSubjectProgress("Mathematics", 6, listOf("Math Theory", "Math Notes", "Math Revision"))
        val physicsProg = calculateSubjectProgress("Physical Sciences", 4, listOf("Physics Theory", "Physics Notes", "Physics Revision"))
        val lifeProg = calculateSubjectProgress("Life Sciences", 4, listOf("Life Science Theory", "Life Science Notes", "Life Science Revision"))
        val accProg = calculateSubjectProgress("Accounting", 4, listOf("Accounting Theory", "Accounting Notes", "Accounting Revision"))
        val busProg = calculateSubjectProgress("Business Studies", 4, listOf("Business Theory", "Business Notes", "Business Revision"))
        val geoProg = calculateSubjectProgress("Geography", 4, listOf("Geography Theory", "Geography Notes", "Geography Revision"))

        pbMaths.progress = mathProg
        pbPhysicalSci.progress = physicsProg
        pbLifeSciences.progress = lifeProg
        pbAccounting.progress = accProg
        pbBusiness.progress = busProg
        pbGeography.progress = geoProg

        val overallAverage = (mathProg + physicsProg + lifeProg + accProg + busProg + geoProg) / 6
        tvProgress.text = "Progress this week: $overallAverage%"
    }

    private fun fetchDailyTip() {
        val tvStudyTip = findViewById<TextView>(R.id.tvStudyTip)
        lifecycleScope.launch {
            try {
                val adviceSlip = RetrofitClient.instance.getRandomAdvice()
                tvStudyTip.text = adviceSlip.slip.advice
            } catch (_: Exception) {
                tvStudyTip.text = "Success is the sum of small efforts, repeated day in and day out."
            }
        }
    }

    private fun addToHistory(action: String) {
        if (isGuest) return
        val sharedPrefs = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val history = sharedPrefs.getString("ACTIVITY_HISTORY", "") ?: ""
        val newHistory = "• $action - Just now\n$history"
        sharedPrefs.edit().putString("ACTIVITY_HISTORY", newHistory.take(500)).apply()

        lifecycleScope.launch {
            FirebaseManager.storeActivityHistory("user_123", action)
        }
    }
}
