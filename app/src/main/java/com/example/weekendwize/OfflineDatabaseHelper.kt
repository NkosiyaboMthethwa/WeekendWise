package com.example.weekendwize

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class OfflineAction(
    val id: Long,
    val userId: String,
    val actionType: String, // e.g. "BOOK_SESSION", "SAVE_NOTE", "QUIZ_RESULT"
    val actionData: String,
    val timestamp: Long,
    val isSynced: Boolean
)

class OfflineDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "weekendwize_offline.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_OFFLINE_ACTIONS = "offline_actions"
        const val COLUMN_ID = "id"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_ACTION_TYPE = "action_type"
        const val COLUMN_ACTION_DATA = "action_data"
        const val COLUMN_TIMESTAMP = "timestamp"
        const val COLUMN_IS_SYNCED = "is_synced"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_OFFLINE_ACTIONS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID TEXT NOT NULL,
                $COLUMN_ACTION_TYPE TEXT NOT NULL,
                $COLUMN_ACTION_DATA TEXT NOT NULL,
                $COLUMN_TIMESTAMP INTEGER NOT NULL,
                $COLUMN_IS_SYNCED INTEGER DEFAULT 0
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_OFFLINE_ACTIONS")
        onCreate(db)
    }

    fun addOfflineAction(userId: String, actionType: String, actionData: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, userId)
            put(COLUMN_ACTION_TYPE, actionType)
            put(COLUMN_ACTION_DATA, actionData)
            put(COLUMN_TIMESTAMP, System.currentTimeMillis())
            put(COLUMN_IS_SYNCED, 0)
        }
        return db.insert(TABLE_OFFLINE_ACTIONS, null, values)
    }

    fun getUnsyncedActions(): List<OfflineAction> {
        val list = mutableListOf<OfflineAction>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_OFFLINE_ACTIONS,
            null,
            "$COLUMN_IS_SYNCED = ?",
            arrayOf("0"),
            null,
            null,
            "$COLUMN_TIMESTAMP ASC"
        )

        cursor.use { c ->
            while (c.moveToNext()) {
                val action = OfflineAction(
                    id = c.getLong(c.getColumnIndexOrThrow(COLUMN_ID)),
                    userId = c.getString(c.getColumnIndexOrThrow(COLUMN_USER_ID)),
                    actionType = c.getString(c.getColumnIndexOrThrow(COLUMN_ACTION_TYPE)),
                    actionData = c.getString(c.getColumnIndexOrThrow(COLUMN_ACTION_DATA)),
                    timestamp = c.getLong(c.getColumnIndexOrThrow(COLUMN_TIMESTAMP)),
                    isSynced = c.getInt(c.getColumnIndexOrThrow(COLUMN_IS_SYNCED)) == 1
                )
                list.add(action)
            }
        }
        return list
    }

    fun markActionSynced(id: Long) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_IS_SYNCED, 1)
        }
        db.update(TABLE_OFFLINE_ACTIONS, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getUnsyncedCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_OFFLINE_ACTIONS WHERE $COLUMN_IS_SYNCED = 0", null)
        var count = 0
        cursor.use {
            if (it.moveToFirst()) {
                count = it.getInt(0)
            }
        }
        return count
    }
}
