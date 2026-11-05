package com.example.weekendwize

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

/**
 * BaseActivity that applies the user's selected language locale across all Activities in the application.
 */
open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
}
