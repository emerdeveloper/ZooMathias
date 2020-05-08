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

    fun saveScore(value: Int) {
        sharedPreferences?.edit()?.putInt("score", value)?.apply()
    }

    fun getCharacter(): String? {
        return sharedPreferences?.getString("character", "")
    }

    fun getScore(): Int {
        return if (sharedPreferences?.contains("score")!!)
            sharedPreferences?.getInt("score", 0)!!
        else 0
    }
}