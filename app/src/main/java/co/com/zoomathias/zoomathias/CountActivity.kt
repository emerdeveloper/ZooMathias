package co.com.zoomathias.zoomathias

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import co.com.zoomathias.zoomathias.businesslogic.CharactersShopBrain
import co.com.zoomathias.zoomathias.businesslogic.CountBrain
import co.com.zoomathias.zoomathias.utils.AnimatorListenerAdapter
import co.com.zoomathias.zoomathias.utils.Constants
import co.com.zoomathias.zoomathias.utils.CustomSharedPreferences
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_count.*

class CountActivity : AppCompatActivity() {

    private var countBrain = CountBrain()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        shuffleQuestion()
        option_one.setOnClickListener { view -> onSelectAnswer(view)  }
        option_two.setOnClickListener { view -> onSelectAnswer(view)  }
        option_three.setOnClickListener { view -> onSelectAnswer(view)  }
        img_return.setOnClickListener { finish() }
    }

    private fun shuffleQuestion() {
        animationNumbers()
        countBrain.showImages(numberImages(), animalImages())
        animationAnimals()
    }

    private fun onSelectAnswer(view : View) {
        var positionSelected = -1
        when (view.id) {
            R.id.option_one -> positionSelected = 0
            R.id.option_two -> positionSelected = 1
            R.id.option_three -> positionSelected = 2
            else -> Toast.makeText(this, "Answer no valid", Toast.LENGTH_SHORT).show()
        }
        validateAnswers(positionSelected)
    }

    private fun validateAnswers(positionSelected : Int)
    {
        if (countBrain.isValidAnswers(positionSelected))
        {
            showMessageDialog()
        } else {
            showMessageDialog(true)
        }
    }

    private fun validateScore(messageDialog: AlertDialog)
    {
        if (countBrain.isFinishAttempts())
        {
            if (countBrain.isWinner())
            {
                val sharedPreferences = CustomSharedPreferences(this)
                val score = sharedPreferences.getScore() + Constants.POINT_BY_WIN
                sharedPreferences.saveScore(score)
                showMessageWinner(score)
                playWinner()
            }
            else
            {
                showMessageFailed()
            }
        }
        else
        {
            shuffleQuestion()
        }
        messageDialog.cancel()
    }

    private fun numberImages(): List<ImageView> {
        return listOf<ImageView>(img_number_left, img_number_center,img_number_right)
    }

    private fun animalImages(): List<ImageView> {
        return listOf<ImageView>(animal_one, animal_two, animal_three,
            animal_four, animal_five, animal_six,
            animal_seven, animal_eight, animal_nine)
    }

    private fun animationAnimals() {
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.sequencialdown_up)
        containerAnimals.startAnimation(animation)
    }

    private fun animationNumbers() {
        var animation : Animation = AnimationUtils.loadAnimation(this, R.anim.sequencialup_down)
        option_one.startAnimation(animation)
        option_two.startAnimation(animation)
        option_three.startAnimation(animation)
    }

    private fun showMessageDialog(isFail : Boolean = false) {
        var builder = AlertDialog.Builder(this)
        var dialogView = this.layoutInflater.inflate(R.layout.message_dialog_congratulations,null)
        builder.setView(dialogView)
        var messageDialog = builder.show()
        messageDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        messageDialog.setCancelable(false)
        messageDialog.setCanceledOnTouchOutside(false)
        var acceptButton = dialogView.findViewById<TextView>(R.id.btn_accept)
        var animation = dialogView.findViewById<LottieAnimationView>(R.id.animation)
        var containerMessageDialog = dialogView.findViewById<ConstraintLayout>(R.id.containerMessageDialog)
        var containerStar = dialogView.findViewById<ConstraintLayout>(R.id.containerStar)
        containerStar.visibility = View.GONE

        if (isFail)
        {
            containerMessageDialog.setBackgroundColor(Color.WHITE);
            animation.setAnimation("oops.json")
            animation.playAnimation()
        }

        var animationAdapter = AnimatorListenerAdapter(
            onStart = { playSound(isFail) },
            onEnd = {
                validateScore(messageDialog)
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        acceptButton.setOnClickListener { validateScore(messageDialog)}
    }

    private fun playSound(isFail : Boolean = false) {
        var mediaPlayer: MediaPlayer = if (isFail) MediaPlayer.create(this, R.raw.failed) else {
            MediaPlayer.create(this, R.raw.success_v2)
        }
        mediaPlayer?.start()
    }

    private fun showMessageWinner(starNumber: Int) {
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
        var containerStar = dialogView.findViewById<ConstraintLayout>(R.id.containerStar)
        animationConfeti.visibility = View.VISIBLE
        containerStar.visibility = View.GONE

        animation.setAnimation("smile.json")
        animation.playAnimation()
        //animation.repeatCount = 3
        animationConfeti.setAnimation("confeti.json")
        animationConfeti.playAnimation()
        animationConfeti.repeatCount = LottieDrawable.INFINITE

        var animationAdapter = AnimatorListenerAdapter(
            onStart = {  },
            onEnd = {
                animation2.visibility = View.VISIBLE
                animation.visibility = View.GONE
                animation2.setAnimation("star.json")
                animation2.playAnimation()
                animation.repeatCount = 2
            },
            onCancel = {},
            onRepeat = {}
        )

        var animationAdapter2 = AnimatorListenerAdapter(
            onStart = {  },
            onEnd = {
                containerStar.visibility = View.VISIBLE
                animation2.visibility = View.GONE
                showStarts(starNumber, dialogView)
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        animation2.addAnimatorListener(animationAdapter2)
        acceptButton.setOnClickListener { finishActivity(messageDialog)}
    }

    private fun playWinner() {
        var mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.winner)
        mediaPlayer?.start()
    }

    private fun playGameOver() {
        var mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.try_again)
        mediaPlayer?.start()
    }

    private fun showMessageFailed() {
        var builder = AlertDialog.Builder(this)
        var dialogView = this.layoutInflater.inflate(R.layout.message_dialog_congratulations,null)
        builder.setView(dialogView)
        var messageDialog = builder.show()
        messageDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        messageDialog.setCancelable(false)
        messageDialog.setCanceledOnTouchOutside(false)
        var acceptButton = dialogView.findViewById<TextView>(R.id.btn_accept)
        var animation = dialogView.findViewById<LottieAnimationView>(R.id.animation)
        var animationConfetti = dialogView.findViewById<LottieAnimationView>(R.id.animationConfeti)
        animationConfetti.visibility = View.GONE
        var containerStar = dialogView.findViewById<ConstraintLayout>(R.id.containerStar)
        containerStar.visibility = View.GONE

        animation.setAnimation("sad.json")
        animation.playAnimation()
        animation.repeatCount = 2

        playGameOver()

        var animationAdapter = AnimatorListenerAdapter(
            onStart = { },
            onEnd = {
                finishActivity(messageDialog)
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        acceptButton.setOnClickListener { finishActivity(messageDialog)}
    }


    private fun finishActivity(messageDialog: AlertDialog) {
        messageDialog.cancel()
        finish()
    }

    private fun showStarts(starNumber: Int, view: View) {
        val characterShopBrain = CharactersShopBrain(starNumber)
        characterShopBrain.showUserStars(starImages(view))
    }

    private fun starImages(view: View): List<ImageView> {
        return listOf<ImageView>(view.findViewById<ImageView>(R.id.star_two),
            view.findViewById<ImageView>(R.id.star_three),
            view.findViewById<ImageView>(R.id.star_four),
            view.findViewById<ImageView>(R.id.star_five),
            view.findViewById<ImageView>(R.id.star_six),
            view.findViewById<ImageView>(R.id.star_seven),
            view.findViewById<ImageView>(R.id.star_eight),
            view.findViewById<ImageView>(R.id.star_nine))
    }
}
