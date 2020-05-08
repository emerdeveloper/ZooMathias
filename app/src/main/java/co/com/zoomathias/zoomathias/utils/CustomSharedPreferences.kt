package co.com.zoomathias.zoomathias.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity


class CustomSharedPreferences (
    activity: AppCompatActivity?
) {

    var sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("co.com.zoomathias.zoomathias", Context.MODE_PRIVATE)

    fun saveCharacter(value: String) {
        sharedPreferences?.edit()?.putString("character", value)?.apply()
    }

    fun getCharacter(): String? {
        return sharedPreferences?.getString("character", "")
    }
}