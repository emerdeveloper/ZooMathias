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
import co.com.zoomathias.zoomathias.utils.AnimatorListenerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.activity_count.*
import kotlin.random.Random

class CountActivity : AppCompatActivity() {

    private var correctOption: Int = 0
    private var positionCorrectOption: Int = 0
    private var score : Int = 0
    private var Attempts: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        shuffleQuestion()
        //containerAnimals.setOnClickListener { shuffleQuestion() }
        option_one.setOnClickListener { view -> onSelectAnswer(view)  }
        option_two.setOnClickListener { view -> onSelectAnswer(view)  }
        option_three.setOnClickListener { view -> onSelectAnswer(view)  }
        img_return.setOnClickListener { finish() }
    }

    private fun shuffleQuestion() {
        animationNumbers()
        printImages()
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
        Attempts++
        if (positionCorrectOption == positionSelected) {
            score++
            showMessageDialog()
            //Toast.makeText(this, "You answer is correct: " + correctOption + "in position: "+ positionCorrectOption, Toast.LENGTH_SHORT).show()
        }
        else {
            showMessageDialog(true)
            //Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateScore(messageDialog: AlertDialog)
    {
        if (Attempts == 6)
        {
            if (score >= 4)
            {
                showMessageWinner()
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

    private fun printImages() {
        var randomNumbers = getRandomNumbers()
        correctOption = getCorrectOption(randomNumbers)
        positionCorrectOption = randomNumbers.indexOf(correctOption)
        var imagesToPrint = matchImageWithNumbers()

        printCorrectNumberOption(positionCorrectOption, imagesToPrint[correctOption]?.get("number")!!)
        //animal_seven.setImageResource(imagesToPrint[correctOption]?.get("animal")!!)

        printIncorrectNumberOptions(positionCorrectOption, imagesToPrint, randomNumbers)

        printCorrectAnimalOption(correctOption, imagesToPrint[correctOption]?.get("animal")!!)
    }

    private fun printCorrectNumberOption(positionNumber: Int, imageNumber : Int) {
        //img_number_left.setImageResource(images["number"]!!)
        when (positionNumber) {
            0 -> img_number_left.setImageResource(imageNumber)
            1 -> img_number_center.setImageResource(imageNumber)
            2 -> img_number_right.setImageResource(imageNumber)
        }
    }

    private fun printIncorrectNumberOptions(correctPosition: Int, incorrectNumberOptions : HashMap<Int, HashMap<String, Int>>, incorrectOptions: List<Int>) {
        when (correctPosition) {
            0 -> {
                img_number_center.setImageResource(incorrectNumberOptions[incorrectOptions[1]]?.get("number")!!)
                img_number_right.setImageResource(incorrectNumberOptions[incorrectOptions[2]]?.get("number")!!)
            }
            1 -> {
                img_number_left.setImageResource(incorrectNumberOptions[incorrectOptions[2]]?.get("number")!!)
                img_number_right.setImageResource(incorrectNumberOptions[incorrectOptions[0]]?.get("number")!!)
            }
            2 -> {
                img_number_center.setImageResource(incorrectNumberOptions[incorrectOptions[1]]?.get("number")!!)
                img_number_left.setImageResource(incorrectNumberOptions[incorrectOptions[0]]?.get("number")!!)
            }
        }
    }

    private fun printCorrectAnimalOption(numberOfImages: Int, animalImage : Int) {
        var listAnimalImages = listOf<ImageView>(animal_one, animal_two, animal_three,
                                                animal_four, animal_five, animal_six,
                                                animal_seven, animal_eight, animal_nine)

        for (x in listAnimalImages.indices) {
            if (x < (numberOfImages)) {
                if (listAnimalImages[x].visibility == View.GONE)
                {
                    listAnimalImages[x].visibility = View.VISIBLE
                }
                listAnimalImages[x].setImageResource(animalImage)
            }
            else {
                listAnimalImages[x].visibility = View.GONE
            }
        }
    }

    private fun getRandomNumbers(): List<Int> {
        var randomList = List(3) { Random.nextInt(1, 9) }
        var nonRepeatingItemList : MutableList<Int> = mutableListOf()
        var lsitDistic = randomList.distinct()


        if (lsitDistic.size == 3)
        {
            return randomList
        }

        for (i in lsitDistic )
        {
            nonRepeatingItemList.add(i)
        }

        if (nonRepeatingItemList.size < 3)
        {
            var index = nonRepeatingItemList.size
            do {
                var random = (1..9).random()
                if (!nonRepeatingItemList.contains(random))
                {
                    nonRepeatingItemList.add(index, random)
                    index++
                }
            } while (nonRepeatingItemList.size < 3)

        }
        return nonRepeatingItemList
    }

    private fun getCorrectOption(randomList: List<Int>) : Int {
        return randomList[(0..2).random()]
    }

    private fun matchImageWithNumbers() : HashMap<Int, HashMap<String, Int>> {
        val rootHashmap = HashMap<Int, HashMap<String, Int>>()
        var ImagesItems = HashMap<String, Int>()

        ImagesItems["number"] = R.drawable.number_one_recognize
        ImagesItems["animal"] =  R.drawable.animal_cat
        rootHashmap[1] = ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_two_recognize
        ImagesItems["animal"] =  R.drawable.animal_pork
        rootHashmap[2] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_three_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[3] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_four_recognize
        ImagesItems["animal"] =  R.drawable.animal_lion
        rootHashmap[4] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_five_recognize
        ImagesItems["animal"] =  R.drawable.animal_chicken
        rootHashmap[5] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_six_recognize
        ImagesItems["animal"] =  R.drawable.animal_mouse
        rootHashmap[6] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_seven_recognize
        ImagesItems["animal"] =  R.drawable.animal_cow
        rootHashmap[7] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_eight_recognize
        ImagesItems["animal"] =  R.drawable.animal_elephant
        rootHashmap[8] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_nine_recognize
        ImagesItems["animal"] =  R.drawable.animal_rabbit
        rootHashmap[9] =  ImagesItems
        return rootHashmap
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

    private fun showMessageWinner() {
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
        animationConfeti.visibility = View.VISIBLE

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
                messageDialog.cancel()
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        animation2.addAnimatorListener(animationAdapter2)
        acceptButton.setOnClickListener { messageDialog.cancel()}
    }

    private fun playWinner() {
        var mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.winner)
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

        animation.setAnimation("sad.json")
        animation.playAnimation()
        animation.repeatCount = 2

        var animationAdapter = AnimatorListenerAdapter(
            onStart = { },
            onEnd = {
                messageDialog.cancel()
            },
            onCancel = {},
            onRepeat = {}
        )

        animation.addAnimatorListener(animationAdapter)
        acceptButton.setOnClickListener { messageDialog.cancel()}
    }
}
