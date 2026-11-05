package com.example.weekendwize

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object OfflineSyncManager {

    private const val TAG = "OfflineSyncManager"

    /**
     * Performs synchronization of all pending unsynced offline actions stored in local SQLite database.
     */
    fun performSync(context: Context, onSyncComplete: ((Int) -> Unit)? = null) {
        val dbHelper = OfflineDatabaseHelper(context)
        val pendingActions = dbHelper.getUnsyncedActions()

        if (pendingActions.isEmpty()) {
            Log.d(TAG, "No pending offline actions to sync.")
            onSyncComplete?.invoke(0)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            var syncedCount = 0
            for (action in pendingActions) {
                try {
                    // Upload/sync action to Cloud Firebase NoSQL store
                    FirebaseManager.storeActivityHistory(action.userId, "[SYNCED] ${action.actionData}")
                    dbHelper.markActionSynced(action.id)
                    syncedCount++
                    Log.d(TAG, "Successfully synced action ID: ${action.id}")
                } catch (e: Exception) {
                    Log.e(TAG, "Error syncing action ID: ${action.id}", e)
                }
            }

            if (syncedCount > 0) {
                NotificationHelper.showNotification(
                    context,
                    "Offline Sync Complete",
                    "Successfully synchronized $syncedCount offline action(s) to the cloud!"
                )
            }

            CoroutineScope(Dispatchers.Main).launch {
                onSyncComplete?.invoke(syncedCount)
            }
        }
    }
}
