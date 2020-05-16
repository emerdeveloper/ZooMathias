package co.com.zoomathias.zoomathias.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import co.com.zoomathias.zoomathias.R

class CountViewPagerAdapter(private val mContext: Context) : PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater

    private var numberImages = numberResourceImages()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = layoutInflater.inflate(R.layout.slider_recognize, container, false)

        var numberImages = view.findViewById<ImageView>(R.id.img_number)
        var handImages = view.findViewById<ImageView>(R.id.img_hand)
        var animalImages = view.findViewById<ImageView>(R.id.animal_recognize)

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, o : Any): Boolean {
        return view == o as ConstraintLayout
    }

    override fun getCount(): Int {
        return numberImages.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, o: Any) {
        container.removeView(o as ConstraintLayout)
    }

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
        return characters
    }
}