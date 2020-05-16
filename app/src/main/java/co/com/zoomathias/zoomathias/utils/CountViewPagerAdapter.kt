package co.com.zoomathias.zoomathias.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import co.com.zoomathias.zoomathias.R
import co.com.zoomathias.zoomathias.businesslogic.RecognizeBrain

class CountViewPagerAdapter(private val mContext: Context) : PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater
    private val recognizeBrain = RecognizeBrain()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = layoutInflater.inflate(R.layout.slider_recognize, container, false)

        var numberImage = view.findViewById<ImageView>(R.id.img_number)
        var handImageOne = view.findViewById<ImageView>(R.id.img_hand)
        var handImageTwo = view.findViewById<ImageView>(R.id.img_hand2)
        var animalImage = view.findViewById<ImageView>(R.id.animal_recognize)
        var animalName = view.findViewById<TextView>(R.id.labelAnimalName)

        recognizeBrain.showAnimal(animalImage, animalName, position)
        recognizeBrain.showNumber(numberImage, position)
        recognizeBrain.showHands(handImageOne, handImageTwo, position)

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, o : Any): Boolean {
        return view == o as ConstraintLayout
    }

    override fun getCount(): Int {
        return recognizeBrain.getResourceCount()
    }

    override fun destroyItem(container: ViewGroup, position: Int, o: Any) {
        container.removeView(o as ConstraintLayout)
    }
}