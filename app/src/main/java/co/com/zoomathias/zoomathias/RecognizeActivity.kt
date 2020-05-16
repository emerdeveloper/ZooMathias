package co.com.zoomathias.zoomathias

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import co.com.zoomathias.zoomathias.utils.CountViewPagerAdapter
import kotlinx.android.synthetic.main.activity_recognize.*

class RecognizeActivity : AppCompatActivity() {

    lateinit var sliderAdapter: CountViewPagerAdapter
    lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)

        img_return.setOnClickListener { finish() }
        sliderAdapter = CountViewPagerAdapter(this)
        viewPagerContainer.adapter = sliderAdapter
        img_background.tag = "jungle"

        addIndicators(0)
        viewPagerContainer?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                addIndicators(position)
                if (position == 1 || position == 2) {
                    if (img_background.tag == "jungle") {
                        img_background.setImageResource(R.drawable.img_count_background_water)
                        img_background.tag = "water"
                    }
                } else {
                    if (img_background.tag == "water") {
                        img_background.setImageResource(R.drawable.img_count_background_jungle)
                        img_background.tag = "jungle"
                    }
                }
            }

        })
    }

    private fun addIndicators(position: Int) {
        dots = ArrayList<TextView>()
        container_indicators.removeAllViews()

        for (index in 0..8) {
            var textView = TextView(this)
            textView.text = Html.fromHtml("&#8226;")
            textView.textSize = 35f
            textView.setTextColor(resources.getColor(R.color.colorAccent))

            dots.add(textView)
            container_indicators.addView(dots[index])
        }

        for (i in dots.indices) {
            dots[position].setTextColor(resources.getColor(R.color.colorWhite))
        }
    }
}