package co.com.zoomathias.zoomathias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.zoomathias.zoomathias.models.Character
import co.com.zoomathias.zoomathias.utils.CharacterRecyclerViewAdapter
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activity_shop.img_return

class ShopActivity : AppCompatActivity() {

    private var userStars: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        recyclerView.layoutManager =  LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = CharacterRecyclerViewAdapter(addCharacteres(), {
                characterSelected : Character -> barberItemClicked(characterSelected)
        })

        img_return.setOnClickListener { finish() }

        printUserStars()
    }

    private fun barberItemClicked(characterSelected : Character) {
        //Toast.makeText(this, "Clicked: ${characterSelected}", Toast.LENGTH_LONG).show()
        charcter.setAnimation(characterSelected.nameFile)
        charcter.playAnimation()
        charcter.repeatCount = LottieDrawable.INFINITE
        printCharacterStars(characterSelected.starRating)
    }

    private fun addCharacteres() : ArrayList<Character> {
        val characteres: ArrayList<Character> = ArrayList()
        characteres.add(Character("chicken.json", 8))
        characteres.add(Character("mouse.json", 6))
        characteres.add(Character("cat.json", 6))
        characteres.add(Character("pigeon.json", 4))
        characteres.add(Character("bee.json", 5))
        return characteres
    }

    private fun starImages(): List<ImageView> {
        return listOf<ImageView>(star_two, star_three,
            star_four, star_five, star_six,
            star_seven, star_eight, star_nine)
    }

    private fun printUserStars() {
        var listStarImages = starImages()
        for (x in listStarImages.indices) {
            if (x < userStars) {
                listStarImages[x].setImageResource(R.drawable.img_star)
            }
            else
            {
                listStarImages[x].visibility = View.GONE
            }
        }
    }

    private fun printCharacterStars(characterStarRating: Int) {
        printUserStars()
        var listStarImages = starImages()
        if (userStars < characterStarRating) {
            for (x in (userStars) until characterStarRating) {
                listStarImages[x].setImageResource(R.drawable.img_star_disabled)
                listStarImages[x].visibility = View.VISIBLE
            }
        }
        else if (userStars >= characterStarRating) {
            for (x in (userStars) until listStarImages.size) {
                if (listStarImages[x].visibility == View.VISIBLE) {
                    listStarImages[x].visibility = View.GONE
                }
            }
        }
        /*else {
            for (x in (userStars) until listStarImages.size) {
                if (listStarImages[x].visibility == View.GONE) {
                    listStarImages[x].visibility = View.VISIBLE
                    listStarImages[x].setImageResource(R.drawable.img_star)
                }
            }
        }*/
    }
}
