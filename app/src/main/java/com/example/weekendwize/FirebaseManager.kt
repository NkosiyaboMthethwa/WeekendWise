package com.example.weekendwize

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File

object FirebaseManager {
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    // --- NoSQL (Firestore) Operations ---

    /**
     * Stores user activity history in Firestore (NoSQL Store)
     */
    suspend fun storeActivityHistory(userId: String, action: String) {
        val activity = hashMapOf(
            "action" to action,
            "timestamp" to System.currentTimeMillis()
        )
        try {
            db.collection("users").document(userId)
                .collection("history").add(activity).await()
            Log.d("FirebaseManager", "Activity stored successfully")
        } catch (e: Exception) {
            Log.e("FirebaseManager", "Error storing activity", e)
        }
    }

    /**
     * Reads activity history from Firestore (NoSQL Read)
     */
    suspend fun getActivityHistory(userId: String): List<String> {
        return try {
            val snapshot = db.collection("users").document(userId)
                .collection("history")
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(10)
                .get().await()
            
            snapshot.documents.map { doc ->
                val action = doc.getString("action") ?: ""
                "• $action"
            }
        } catch (e: Exception) {
            Log.e("FirebaseManager", "Error reading activity", e)
            emptyList()
        }
    }

    /**
     * Stores user bookings in Firestore
     */
    suspend fun storeBookings(userId: String, sessions: List<String>) {
        val data = hashMapOf("sessions" to sessions)
        try {
            db.collection("users").document(userId)
                .collection("data").document("bookings").set(data).await()
        } catch (e: Exception) {
            Log.e("FirebaseManager", "Error storing bookings", e)
        }
    }

    // --- Blob Storage (Firebase Storage) Operations ---

    /**
     * Uploads a simple text file as a blob to Firebase Storage
     */
    suspend fun uploadStudyLog(userId: String, content: String) {
        val storageRef = storage.reference.child("logs/$userId/study_log.txt")
        try {
            storageRef.putBytes(content.toByteArray()).await()
            Log.d("FirebaseManager", "Blob uploaded successfully")
        } catch (e: Exception) {
            Log.e("FirebaseManager", "Error uploading blob", e)
        }
    }

    /**
     * Example: Upload a local file as a blob
     */
    suspend fun uploadFile(userId: String, localFile: File) {
        val storageRef = storage.reference.child("uploads/$userId/${localFile.name}")
        try {
            storageRef.putFile(android.net.Uri.fromFile(localFile)).await()
        } catch (e: Exception) {
            Log.e("FirebaseManager", "Error uploading file", e)
        }
    }
}
