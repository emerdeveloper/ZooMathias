package co.com.zoomathias.zoomathias

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.zoomathias.zoomathias.models.Character
import co.com.zoomathias.zoomathias.utils.AnimatorListenerAdapter
import co.com.zoomathias.zoomathias.utils.CharacterRecyclerViewAdapter
import co.com.zoomathias.zoomathias.utils.Constants
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activity_shop.img_return

class ShopActivity : AppCompatActivity() {

    private var userStars: Int = 5
    private var characterImage: String = ""
    private var characterStars: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        recyclerView.layoutManager =  LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = CharacterRecyclerViewAdapter(addCharacteres(), {
                characterSelected : Character -> barberItemClicked(characterSelected)
        })

        img_return.setOnClickListener { finishActivity() }
        charcter.setOnClickListener { validateShopCharacter() }
        printUserStars()
    }

    private fun finishActivity() {
        setResult(Constants.RESULT_CODE, Intent().putExtra(Constants.EXTRA_NAME_CHARACTER, characterImage))
        finish()
    }

    private fun barberItemClicked(characterSelected : Character) {
        //Toast.makeText(this, "Clicked: ${characterSelected}", Toast.LENGTH_LONG).show()
        characterImage = characterSelected.nameFile
        characterStars = characterSelected.starRating
        charcter.setAnimation(characterSelected.nameFile)
        charcter.playAnimation()
        charcter.repeatCount = LottieDrawable.INFINITE
        printCharacterStars(characterSelected.starRating)
    }

    private fun validateShopCharacter() {
        showMessageDialogCannotGetCharacter((userStars >= characterStars))
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
    }

    private fun showMessageDialogCannotGetCharacter(isEnabled : Boolean) {
        var builder = AlertDialog.Builder(this)
        var dialogView = this.layoutInflater.inflate(R.layout.message_dialog_congratulations,null)
        builder.setView(dialogView)
        var messageDialog = builder.show()
        messageDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        messageDialog.setCancelable(false)
        messageDialog.setCanceledOnTouchOutside(false)
        var acceptButton = dialogView.findViewById<TextView>(R.id.btn_accept)
        var animation = dialogView.findViewById<LottieAnimationView>(R.id.animation)
        var animation2 = dialogView.findViewById<LottieAnimationView>(R.id.animation2)
        var animationConfeti = dialogView.findViewById<LottieAnimationView>(R.id.animationConfeti)
        animationConfeti.visibility = View.GONE
        animation2.visibility = View.GONE

        if (isEnabled) {
            animation.setAnimation("clap.json")
        }
        else {
            animation.setAnimation("not.json")
        }
        animation.playAnimation()

        var animationAdapter = AnimatorListenerAdapter(
            onStart = {  },
            onEnd = {
                onSelectToGetCharacter(isEnabled, messageDialog)
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        acceptButton.setOnClickListener { onSelectToGetCharacter(isEnabled, messageDialog)}
    }

    private  fun onSelectToGetCharacter(isEnabled : Boolean, messageDialog : AlertDialog) {
        messageDialog.cancel()

        if (isEnabled) {
            finishActivity()
        }
    }
}
