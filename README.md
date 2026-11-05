#  WeekendWize - Grade 11 Study & Revision Mobile App

[![Android CI Build & Automated Tests](https://github.com/NkosiyaboMthethwa/WeekendWise/actions/workflows/build.yml/badge.svg)](https://github.com/NkosiyaboMthethwa/WeekendWise/actions)
![Platform](https://img.shields.io/badge/Platform-Android-green.svg)
![Language](https://img.shields.io/badge/Language-Kotlin-blue.svg)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24%20%28Android%207.0%29-orange.svg)
![Target SDK](https://img.shields.io/badge/Target%20SDK-34%20%28Android%2014%29-red.svg)

**WeekendWize** is an all-in-one mobile companion designed specifically for **Grade 11 High School Students** in South Africa. The application bridges the gap between theoretical learning and active recall testing while keeping students organized with a functional weekend study timetable, real-time cloud synchronization, multi-language accessibility, and automated progress tracking.

---

##  Video Demonstrations
- **Full Video Demonstration**: [Watch on YouTube][(https://youtu.be/i15tblqFos0)](https://youtu.be/r8a_Kw1rW8k)
- https://drive.google.com/file/d/1WngtvoAFG7pXESGfRq7UR2TaLVuWyJEL/view?usp=drive_link 
- **Short Overview / Demo**: [Watch Youtube Short](https://youtube.com/shorts/kQ0tLo0ToB8) 

---

##  Key Features

###  1. Secure Authentication & Single Sign-On (SSO)
- **Encrypted Registration & Login**: Passwords are securely hashed using `SHA-256` with a custom salt before being persisted (`SecurityUtils.kt`). Plaintext passwords are never stored.
- **Single Sign-On (SSO)**: Native integration with **Google Sign-In / Credential Manager** for instant, frictionless account access.
- **Guest Mode**: Read-only browsing for unregistered users to preview study notes without altering progress statistics.

### 2. Multi-Language Support (South African Languages)
Full localization support across 3 languages:
- 🇿🇦 **English** (Default)
- 🇿🇦 **isiZulu** (`res/values-zu`)
- 🇿🇦 **Afrikaans** (`res/values-af`)
- Dynamic language switching implemented via `LocaleHelper.kt`.

### 3. CAPS Curriculum Subjects & Daily Quizzes
Comprehensive study library covering 6 core CAPS Grade 11 subjects:
1.  **Mathematics** (Algebra, Functions & Graphs, Trigonometry)
2.  **Physical Sciences** (Newton's Laws, Chemical Bonding, Stoichiometry)
3.  **Life Sciences** (Photosynthesis, Respiration, Animal Nutrition)
4.  **Accounting** (Partnerships, Cost Accounting, Budgeting)
5.  **Business Studies** (Business Environments, PESTLE, Ventures)
6.  **Geography** (Geomorphology, Slope Elements, Climatology)

*Every topic and subtopic includes interactive multiple-choice quizzes, detailed CAPS theory breakdowns, and quick revision notes.*

###  4. Offline Mode with Automatic Cloud Sync
- **Local Persistence**: Performs offline actions (booking tutoring sessions, completing quizzes) stored in a local **SQLite Database** (`OfflineDatabaseHelper.kt`).
- **Network Observer**: `NetworkMonitor.kt` listens for active internet connections.
- **Auto-Sync Engine**: `OfflineSyncManager.kt` automatically pushes offline actions to **Firebase Firestore** NoSQL database when reconnected, notifying the student upon completion.

###  5. Real-Time Push & Local Notifications
- **Firebase Cloud Messaging (FCM)**: `MyFirebaseMessagingService.kt` handles real-time cloud push notifications.
- **Local Alerts**: `NotificationHelper.kt` creates system `NotificationChannel` alerts for study reminders, streak milestones, and sync confirmations.
- **Interactive Notifications Panel**: Top bar bell icon displays active alerts with options to clear, open, or trigger test pushes.

###  6. Weekend Timetable & Session Bookings
- Interactive weekend schedule for booking Saturday & Sunday study slots.
- Booked sessions dynamically update the Home Dashboard and track study commitments.

### 7. Dynamic Progress Analytics & Dark Mode
- **Progress Tracking**: Dynamic progress bars per subject and overall weekly completion percentage.
- **Dark Mode**: Supports light and dark theme toggling with instant `AppCompatDelegate` night mode application.
- **Live REST API Tip**: Integrates Retrofit 2 with the AdviceSlip REST API to fetch live motivational study tips.

---

## Automated Testing & CI/CD Pipeline

The project includes unit tests and continuous integration setup:

- **Automated Unit Tests**: Located in `app/src/test/java/com/example/weekendwize/AppUnitTests.kt` testing:
  - `SHA-256` password hashing and verification.
  - Data repository subject and quiz completeness.
  - Required CAPS subjects presence.
- **GitHub Actions CI/CD**: `.github/workflows/build.yml` automatically triggers on every push/PR to:
  - Set up JDK 17.
  - Run unit tests (`./gradlew test`).
  - Assemble Debug APK (`./gradlew assembleDebug`).
  - Upload `app-debug.apk` as a downloadable artifact.

---

## Tech Stack & Architecture

| Component | Technology |
| :--- | :--- |
| **Language** | Kotlin (1.9.22) |
| **UI Toolkit** | XML Layouts with Material Design 3 |
| **Architecture** | Activity-based with Centralized Repository (`DataRepository.kt`) |
| **Async Operations** | Kotlin Coroutines & `lifecycleScope` |
| **Networking** | Retrofit 2 + Gson Converter |
| **Local Database** | SQLite (`SQLiteOpenHelper`) & `SharedPreferences` |
| **Cloud Database** | Firebase Firestore (NoSQL) |
| **Blob Storage** | Firebase Storage |
| **Push Notifications**| Firebase Cloud Messaging (FCM) & `NotificationManager` |
| **Security** | SHA-256 Hashing with Salt (`SecurityUtils.kt`) |
| **Build System** | Gradle 8.7 (AGP 8.2.2) |
| **CI/CD** | GitHub Actions |

---

## Project Structure

```
WeekendWize/
├── .github/
│   └── workflows/
│       └── build.yml               # GitHub Actions CI Configuration
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/weekendwize/
│   │   │   │   ├── ApiService.kt            # Retrofit REST API interface
│   │   │   │   ├── DataRepository.kt        # Subjects, Quizzes & Theory Data
│   │   │   │   ├── FirebaseManager.kt       # Firestore & Storage operations
│   │   │   │   ├── HomeActivity.kt          # Main Dashboard & Navigation
│   │   │   │   ├── LocaleHelper.kt          # Multi-language locale manager
│   │   │   │   ├── MainActivity.kt          # Login & SSO Activity
│   │   │   │   ├── Models.kt                # Parcelable Data Models
│   │   │   │   ├── MyFirebaseMessagingService.kt # FCM Push Service
│   │   │   │   ├── NetworkMonitor.kt        # Connectivity observer
│   │   │   │   ├── NotificationHelper.kt    # System notification manager
│   │   │   │   ├── OfflineDatabaseHelper.kt # SQLite offline actions storage
│   │   │   │   ├── OfflineSyncManager.kt    # Cloud sync engine
│   │   │   │   ├── QuizActivity.kt          # Interactive quiz engine
│   │   │   │   ├── RegisterActivity.kt      # Account registration
│   │   │   │   ├── ResourceViewActivity.kt  # Theory & notes viewer
│   │   │   │   ├── RetrofitClient.kt        # Retrofit singleton client
│   │   │   │   ├── SecurityUtils.kt         # SHA-256 encryption helper
│   │   │   │   ├── SettingsActivity.kt      # User settings & preferences
│   │   │   │   ├── SplashActivity.kt        # Splash screen
│   │   │   │   ├── SubTopicListActivity.kt  # Subtopics viewer
│   │   │   │   └── TopicListActivity.kt     # Topics viewer
│   │   │   ├── res/
│   │   │   │   ├── layout/                  # XML UI Layouts
│   │   │   │   ├── values/                  # Default English Strings & Colors
│   │   │   │   ├── values-zu/               # isiZulu Translations
│   │   │   │   └── values-af/               # Afrikaans Translations
│   │   │   └── AndroidManifest.xml          # App Manifest & Permissions
│   │   └── test/java/com/example/weekendwize/
│   │       └── AppUnitTests.kt              # JUnit Automated Unit Tests
│   └── build.gradle.kts                     # App Build Dependencies
├── gradle.properties
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## Installation & Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/NkosiyaboMthethwa/WeekendWise.git
   cd WeekendWise
   ```

2. **Open in Android Studio**:
   - Open Android Studio (Jellyfish / Ladybug / 2026.2+).
   - Select **Open an existing project** and navigate to the cloned `WeekendWise` folder.
   - Allow Gradle to sync dependencies automatically.

3. **Build & Run**:
   - Connect an Android Device (Android 7.0+) or launch an Emulator.
   - Click **Run `app`** (`Shift + F10`).

4. **Run Unit Tests**:
   - Run tests via terminal:
     ```bash
     ./gradlew test
     ```
   - Or right-click `AppUnitTests.kt` in Android Studio and select **Run 'AppUnitTests'**.

5. **Generate Debug APK**:
   ```bash
   ./gradlew assembleDebug
   ```
   The generated APK will be located at:
   `app/build/outputs/apk/debug/app-debug.apk`

---

