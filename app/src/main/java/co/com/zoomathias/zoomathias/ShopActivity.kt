package co.com.zoomathias.zoomathias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.zoomathias.zoomathias.utils.CharacterRecyclerViewAdapter
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_count.*
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activity_shop.img_return

class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        recyclerView.layoutManager =  LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = CharacterRecyclerViewAdapter(addCharacteres(), {
                characterSelected : String -> barberItemClicked(characterSelected)
        })

        img_return.setOnClickListener { finish() }
    }

    private fun barberItemClicked(characterSelected : String) {
        Toast.makeText(this, "Clicked: ${characterSelected}", Toast.LENGTH_LONG).show()
        charcter.setAnimation(characterSelected)
        charcter.playAnimation()
        charcter.repeatCount = LottieDrawable.INFINITE
    }

    private fun addCharacteres() : ArrayList<String> {
        val characteres: ArrayList<String> = ArrayList()
        characteres.add("chicken.json")
        characteres.add("mouse.json")
        characteres.add("cat.json")
        return characteres
    }

}
