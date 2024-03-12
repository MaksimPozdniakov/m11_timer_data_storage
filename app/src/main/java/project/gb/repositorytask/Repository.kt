package project.gb.repositorytask

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "preference_name"
private const val SHARED_PREFS_KEY = "shared_prefs_name"

class Repository (private val context: Context) {

    private var localeValue: String? = null
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun getText(): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
            else -> "no data was found"
        }
    }

    private fun getDataFromSharedPreference(context: Context): String? {
        return sharedPreferences.getString(SHARED_PREFS_KEY, null)
    }

    fun saveText(text: String) {
        with(sharedPreferences.edit()) {
            putString(SHARED_PREFS_KEY, text)
            apply()
        }
        localeValue = text
    }

    fun clearText() {
        with(sharedPreferences.edit()) {
            remove(SHARED_PREFS_KEY)
            apply()
        }
        localeValue = null
    }

    private fun getDataFromLocalVariable(): String? {
        return localeValue
    }
}

