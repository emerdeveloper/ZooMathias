package co.com.zoomathias.zoomathias

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.zoomathias.zoomathias.businesslogic.CharactersShopBrain
import co.com.zoomathias.zoomathias.utils.AnimatorListenerAdapter
import co.com.zoomathias.zoomathias.utils.CharacterRecyclerViewAdapter
import co.com.zoomathias.zoomathias.utils.Constants
import co.com.zoomathias.zoomathias.utils.CustomSharedPreferences
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activity_shop.img_return

class ShopActivity : AppCompatActivity() {


    private lateinit var characterShopBrain: CharactersShopBrain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)


        characterShopBrain = CharactersShopBrain(getScore())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = CharacterRecyclerViewAdapter(characterShopBrain.getCharacters(), this::barberItemClicked)

        img_return.setOnClickListener { finishActivity() }
        charcter.setOnClickListener { validateShopCharacter() }
        characterShopBrain.showUserStars(starImages())
    }

    private fun finishActivity() {
        if (characterShopBrain.boughtCharacter()) {

            setResult(
                Constants.RESULT_CODE,
                Intent().putExtra(Constants.EXTRA_NAME_CHARACTER, characterShopBrain.getCharacterImage())
            )
            val sharedPreferences = CustomSharedPreferences(this)
            sharedPreferences.saveCharacter(characterShopBrain.getCharacterImage())
        }
        finish()
    }

    private fun getScore(): Int {
        val sharedPreferences = CustomSharedPreferences(this)
        return sharedPreferences.getScore()
    }

    private fun barberItemClicked(characterSelected : Int) {
        //Toast.makeText(this, "Clicked: ${characterSelected}", Toast.LENGTH_LONG).show()
        characterShopBrain.characterSelected(characterSelected)
        charcter.setAnimation(characterShopBrain.getCharacterImage())
        charcter.playAnimation()
        charcter.repeatCount = LottieDrawable.INFINITE
        showStars()
    }

    private fun validateShopCharacter() {
        showMessageDialogCannotGetCharacter()
    }

    private fun starImages(): List<ImageView> {
        return listOf<ImageView>(star_two, star_three,
            star_four, star_five, star_six,
            star_seven, star_eight, star_nine)
    }

    private fun showStars() {
        characterShopBrain.showUserStars(starImages())
        characterShopBrain.showCharacterStars(starImages())
    }

    private fun showMessageDialogCannotGetCharacter() {
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

        if (characterShopBrain.isCharacterAvailableToBuy()) {
            animation.setAnimation("clap.json")
        }
        else {
            animation.setAnimation("not.json")
        }
        animation.playAnimation()

        var animationAdapter = AnimatorListenerAdapter(
            onStart = {  },
            onEnd = {
                onSelectToGetCharacter(messageDialog)
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        acceptButton.setOnClickListener { onSelectToGetCharacter(messageDialog)}
    }

    private  fun onSelectToGetCharacter(messageDialog : AlertDialog) {
        messageDialog.cancel()

        if (characterShopBrain.isCharacterAvailableToBuy()) {
            finishActivity()
        }
    }
}
