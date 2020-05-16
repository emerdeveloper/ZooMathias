package co.com.zoomathias.zoomathias.businesslogic

import android.view.View
import android.widget.ImageView
import co.com.zoomathias.zoomathias.R

class RecognizeBrain {

    //number
    //hand
    //Animal

    private fun numberResourceImages() : ArrayList<Int> {
        val characters: ArrayList<Int> = ArrayList()
        characters.add(R.drawable.number_one_recognize)
        characters.add(R.drawable.number_two_recognize)
        characters.add(R.drawable.number_three_recognize)
        characters.add(R.drawable.number_four_recognize)
        characters.add(R.drawable.number_five_recognize)
        characters.add(R.drawable.number_six_recognize)
        characters.add(R.drawable.number_seven_recognize)
        characters.add(R.drawable.number_eight_recognize)
        characters.add(R.drawable.number_nine_recognize)
        return characters
    }

    private fun handResourceImages() : ArrayList<Int> {
        val characters: ArrayList<Int> = ArrayList()
        characters.add(R.drawable.hand_number_one)
        characters.add(R.drawable.hand_number_two)
        characters.add(R.drawable.hand_number_three)
        characters.add(R.drawable.hand_number_four)
        characters.add(R.drawable.hand_number_five)
        characters.add(R.drawable.number_six_recognize)
        characters.add(R.drawable.number_seven_recognize)
        characters.add(R.drawable.number_eight_recognize)
        characters.add(R.drawable.number_nine_recognize)
        return characters
    }
}