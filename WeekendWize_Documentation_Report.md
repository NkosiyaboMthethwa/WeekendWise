# WeekendWize Mobile Application
## Comprehensive Technical Documentation Report

**Project Name**: WeekendWize  
**Target Audience**: Grade 11 CAPS Learners (South Africa)  
**CI Status**: GitHub Actions Automated Build & Testing (Passing)

---

## 1. Executive Summary & Purpose of the App

**WeekendWize** is an all-in-one educational mobile application developed specifically for Grade 11 High School learners in South Africa following the CAPS curriculum. Grade 11 is a crucial academic milestone where learners prepare for national matriculation exams. However, many learners struggle with balancing theoretical reading, active recall self-testing, and maintaining structured weekend study schedules.

The primary **purpose** of WeekendWize is to bridge the gap between learning (theory & notes) and testing (interactive quizzes), while empowering learners with smart weekend study timetables, real-time push notification alerts, multi-language support (English, isiZulu, Afrikaans), and offline synchronization capabilities.

### 1.1 Target Subjects Covered
- **Mathematics** (Algebra, Functions & Graphs, Trigonometry & Geometry)
- **Physical Sciences** (Newton's Laws, Chemical Bonding, Stoichiometry, Ideal Gases)
- **Life Sciences** (Photosynthesis, Cellular Respiration, Animal Nutrition, Excretion)
- **Accounting** (Partnerships, Financial Statements, Cost Accounting, Budgeting)
- **Business Studies** (Micro & Macro Environments, PESTLE, Entrepreneurship, Leadership)
- **Geography** (Geomorphology, Slope Elements, Climatology, Synoptic Maps)

---

## 2. Design Considerations & Architecture

### 2.1 User Experience (UX) & Interface Architecture
The application follows Material Design 3 guidelines featuring a cohesive teal color palette (`#004D40`) tailored for educational environments. The core layout utilizes a bottom navigation system separating four primary scenes:
- **Home Dashboard**: Displays daily quiz shortcuts, weekly progress indicators, booked weekend sessions, and live motivational study tips.
- **Revision & Notes Library**: Organizes subject theory guides, concise study notes, and exam preparation tips.
- **Weekend Timetable & Bookings**: Enables interactive booking of Saturday and Sunday tutoring and revision slots.
- **My Progress Analytics**: Visualizes subject-specific performance using dynamic progress bars.

### 2.2 Multi-Language Support (South African Languages)
To ensure linguistic inclusivity, WeekendWize provides complete multi-language support covering 3 official South African languages:
- **English** (Default: `res/values/strings.xml`)
- **isiZulu** (South African Language: `res/values-zu/strings.xml`)
- **Afrikaans** (South African Language: `res/values-af/strings.xml`)

Language switching is powered by `LocaleHelper.kt`, which updates the application configuration dynamically at runtime without requiring an app restart.

### 2.3 Security & Password Encryption Architecture
User authentication security is enforced via `SecurityUtils.kt` using cryptographic **SHA-256 hashing with a custom salt**. Plaintext passwords are never stored in `SharedPreferences` or database storage. In addition, single sign-on (**SSO**) is supported via Google Identity / Credential Manager integration for frictionless 1-tap sign in.

### 2.4 Offline-First Architecture & Cloud Synchronization
Recognizing that learners may have intermittent internet access, WeekendWize implements a robust offline-first architecture:
- **SQLite Local Storage (`OfflineDatabaseHelper.kt`)**: Stores offline actions (session bookings, completed quizzes, viewed notes) locally when the device has no active internet connection.
- **Network State Observer (`NetworkMonitor.kt`)**: Monitors network connectivity changes automatically using Android's `ConnectivityManager` network callbacks.
- **Automated Cloud Sync (`OfflineSyncManager.kt`)**: When an active internet connection is detected, pending offline actions are automatically uploaded to **Firebase Firestore** (NoSQL Cloud Store), and a completion notification is issued to the learner.

### 2.5 Real-Time Notification System
Real-time push notifications are handled by `MyFirebaseMessagingService.kt` using Firebase Cloud Messaging (FCM). Local reminders, streak alerts, and sync updates are created via `NotificationHelper.kt` using system `NotificationChannel` (API 26+). A dedicated top-bar bell icon  allows learners to review, clear, or test notifications.

### 2.6 REST API Integration
The app uses **Retrofit 2** and **Gson Converter** (`RetrofitClient.kt` & `ApiService.kt`) to connect to an external REST API (AdviceSlip API). This fetches live educational study advice and motivational tips asynchronously using Kotlin Coroutines (`lifecycleScope`) with offline fallback data.

---

## 3. Utilisation of GitHub & GitHub Actions

### 3.1 Version Control Strategy
Version control is managed using Git and hosted on GitHub at `https://github.com/NkosiyaboMthethwa/WeekendWise.git`. The repository maintains a clean commit history on the `master` branch, incorporating atomic commits for every feature addition, UI enhancement, and bug fix.

### 3.2 Automated Testing Strategy (JUnit)
Automated testing is implemented under `app/src/test/java/com/example/weekendwize/AppUnitTests.kt` using JUnit 4. The test suite covers core business logic:
- `testPasswordHashingAndVerification()`: Validates cryptographic SHA-256 hashing and salted password matching.
- `testDataRepositorySubjectCompleteness()`: Verifies that every subject, topic, and subtopic contains complete quizzes, questions, theory, and revision notes.
- `testSubjectListContainsRequiredSubjects()`: Confirms that all 6 required CAPS subjects are initialized.

### 3.3 Continuous Integration Pipeline (GitHub Actions CI)
Automated continuous integration is configured in `.github/workflows/build.yml`. The pipeline triggers automatically on every push or pull request to the `master` branch.

| Pipeline Step | GitHub Action / Command | Purpose |
| :--- | :--- | :--- |
| **1. Checkout Repository** | `actions/checkout@v4` | Fetches the latest source code from GitHub repository. |
| **2. Set up JDK 17** | `actions/setup-java@v4` | Configures Java 17 Temurin environment with Gradle dependency caching. |
| **3. Execute Permissions** | `chmod +x gradlew` | Grants execution rights to the Gradle wrapper script. |
| **4. Run Unit Tests** | `./gradlew test` | Executes all automated unit tests in headless CI container. |
| **5. Build Debug APK** | `./gradlew assembleDebug` | Compiles the full Android application package (APK). |
| **6. Upload Artifact** | `actions/upload-artifact@v4` | Stores the compiled `app-debug.apk` as a downloadable GitHub artifact. |

```yaml
name: Android CI Build & Automated Tests

on:
  push:
    branches: [ "main", "master" ]
  pull_request:
    branches: [ "main", "master" ]

jobs:
  build:
    name: Build & Test Android App
    runs-on: ubuntu-latest

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: gradle

      - name: Grant Execute Permission for Gradlew
        run: chmod +x gradlew

      - name: Run Automated Unit Tests
        run: ./gradlew test

      - name: Build Debug APK
        run: ./gradlew assembleDebug

      - name: Upload Debug APK Artifact
        uses: actions/upload-artifact@v4
        with:
          name: app-debug
          path: app/build/outputs/apk/debug/app-debug.apk
```

---

## 4. Technical Stack Summary

| Layer | Technologies Used |
| :--- | :--- |
| **Programming Language** | Kotlin (1.9.22) |
| **UI & Layouts** | Android XML, Material Design 3, ConstraintLayout |
| **Security & Encryption** | SHA-256 with Salt, Google Identity SSO |
| **Local Storage** | SQLite (`SQLiteOpenHelper`), SharedPreferences |
| **Cloud Services** | Firebase Firestore (NoSQL), Firebase Cloud Messaging (FCM) |
| **REST API & Async** | Retrofit 2, Gson Converter, Kotlin Coroutines |
| **Automated Testing** | JUnit 4 (`AppUnitTests.kt`) |
| **CI/CD Pipeline** | GitHub Actions (`.github/workflows/build.yml`) |

---

## 5. Conclusion
The WeekendWize application fulfills all requirements of a modern, secure, accessible, and robust educational mobile platform. By combining password encryption, SSO, multi-language localization, offline synchronization, push notifications, comprehensive CAPS subject coverage, and an automated GitHub Actions CI pipeline, the application guarantees reliability both on local devices and in continuous integration environments.
