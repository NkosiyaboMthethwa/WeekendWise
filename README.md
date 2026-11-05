WeekendWize is an all-in-one mobile companion designed specifically for Grade 11 students to help them master their weekend studies and prepare for major exams. 
The app bridges the gap between learning (theory) and testing (quizzes), all while keeping students organized with a functional weekend timetable.
1. Interactive Dashboard (Home):
Daily Quizzes: Instant access to testing modules for Mathematics, Physical Sciences, Life Sciences, and Accounting.
Weekend At-A-Glance: View your currently booked study sessions directly on the main screen.
2. Digital Study Library (Revision & Notes)
Comprehensive Theory breakdowns for core concepts.
Concise Study Notes for quick memory refreshes.
Revision Guides focused on exam preparation.
3. Smart Scheduling (Bookings):
Functional Timetable: An interactive weekend schedule where students can select and book specific tutoring or study slots.
Real-time Updates: Booked sessions automatically sync with the home dashboard.
4. Progress Analytics (Profile):
Visual Performance Tracking using dynamic progress bars.
Insights into which subjects need more focus based on quiz completion.
5. Personalization & Settings:
Dark Mode Support: Toggle between light and dark themes for comfortable late-night studying.
Activity History: Keep track of your recently viewed resources and completed bookings.
Account Management: Securely update your profile and login credentials.
Live Study Tips: The Home dashboard now features a "Daily Study Tip" section that fetches real-time data from a REST API server.
This ensures that every time a student opens the app, they receive fresh motivation or technical advice directly from the cloud.
USER MODES:
Registered User: Full access to all features, including progress tracking and session bookings.
Guest User: Browse theory and notes in "Read-Only" mode. Bookings and progress tracking are disabled to encourage full registration.
TECH STACK:
Language: Kotlin
UI: XML (Material Design)
Architecture: Activity-based with a centralized Data Repository.
Persistence: SharedPreferences for user settings, theme preferences, and activity history.
Tools: Android Studio, Git.
Networking: Retrofit 2 with Gson Converter for REST API communication.
Asynchrony: Kotlin Coroutines (lifecycleScope) for non-blocking network calls.
External Data: Integrated with JSONPlaceholder API to fetch dynamic, real-time study content.



VIDEO LINK {https://youtu.be/i15tblqFos0} {https://youtube.com/shorts/kQ0tLo0ToB8}


