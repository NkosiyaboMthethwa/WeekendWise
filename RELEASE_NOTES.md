#  WeekendWize - (Innovative Features)

We are thrilled to announce the official **v2.0 Release of WeekendWize**! This major release brings a suite of innovative features engineered specifically for Grade 11 High School CAPS learners in South Africa combining offline first persistence, multi-language accessibility, real-time cloud sync, security encryption, and automated CI/CD pipelines.

---

##  What's New in Version 2.0

### 1. Cryptographic Password Security & 1-Tap Single Sign-On (SSO)
- **SHA-256 Salted Hashing**: Replaced plain-text authentication with salted **SHA-256 cryptographic hashing** (`SecurityUtils.kt`). Your credentials are encrypted before storage and verification.
- **Google Single Sign-On (SSO)**: One-tap instant sign-in using Google Identity / Credential Manager, allowing learners to log in seamlessly without typing passwords.

---

### 2. Smart Offline-First Engine & Automatic Cloud Sync
- **Local SQLite Persistence**: Performs offline study bookings, quiz attempts, and resource views stored locally in an **SQLite Database** (`OfflineDatabaseHelper.kt`) when disconnected.
- **Auto-Sync Engine**: Integrated `NetworkMonitor.kt` automatically detects network reconnection and triggers `OfflineSyncManager.kt` to push pending offline data to **Firebase Firestore NoSQL**, issuing a real-time confirmation notification to the user.

---

### 🇿🇦 3. South African Multi-Language Localization Engine
- **3 Official Languages**: Full translation support for:
  - 🇿🇦 **English** (Default)
  - 🇿🇦 **isiZulu** (`res/values-zu`)
  - 🇿🇦 **Afrikaans** (`res/values-af`)
- **Dynamic Runtime Locale Switcher**: Switch languages instantly in **Settings** via `LocaleHelper.kt` without needing to restart your device.

---

### 4. Real-Time Push Notifications & Interactive Alert Center
- **Firebase Cloud Messaging (FCM)**: Remote push notifications delivered via `MyFirebaseMessagingService.kt`.
- **System Notification Channels**: Local study reminders, streak milestones, and sync alerts powered by `NotificationHelper.kt`.
- **Interactive Top-Bar Bell Icon **: Tap the new top-bar notification bell on the Home screen to view active notifications, clear alerts, or run test push notifications.

---

### 5. Complete CAPS Curriculum & Interactive Quizzes (6 Subjects)
- **100% Subject & Subtopic Coverage**: Every single subject and subtopic now contains interactive multiple choice quizzes, questions, theory guides, and revision notes.
- **Added 2 New CAPS Subjects**:
  - **Business Studies** (Business Environments, PESTLE, Entrepreneurship, Leadership)
  - **Geography** (Geomorphology, Slope Elements, Climatology, Synoptic Maps)
- **Expanded Existing Core Subjects**: Mathematics, Physical Sciences, Life Sciences, and Accounting.

---

### 6. Live REST API Study Advice & Dynamic Analytics
- **Live REST API Integration**: Connected to AdviceSlip REST API via **Retrofit 2** & Kotlin Coroutines (`RetrofitClient.kt`) to deliver fresh daily study tips and motivational advice on every app launch.
- **Visual Progress Analytics**: Dynamic progress bars tracking completion percentages across all 6 subjects.
- **Material 3 Dark Mode**: Toggle between light and dark themes for late-night study sessions.

---

### 7. Automated Testing Suite & GitHub Actions CI/CD
- **JUnit 4 Automated Test Harness**: `AppUnitTests.kt` validates password encryption, repository completeness, and CAPS subject integrity.
- **GitHub Actions Cloud CI Workflow**: `.github/workflows/build.yml` automatically compiles code, runs unit tests, and uploads downloadable `app-debug.apk` artifacts on every GitHub push.

---

## Summary of Version 2.0 Highlights
- **SHA-256 Hashing & Google SSO**
- **Offline SQLite Storage & Auto Firebase Sync**
- **English, isiZulu & Afrikaans Support**
- **Real-Time Push Notifications & Top Bar Bell**
- **6 Full CAPS Subjects with 100% Quizzes & Notes**
- **Live Retrofit REST API Study Advice**
- **Automated Unit Tests & GitHub Actions CI/CD**
