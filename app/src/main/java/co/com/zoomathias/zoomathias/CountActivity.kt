package co.com.zoomathias.zoomathias

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_count.*
import kotlin.random.Random

class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        animationNumbers()
        animationAnimals()
        printImages()
        //containerAnimals.visibility = View.GONE
        /*option_one.setOnClickListener { OnClickOptionOne() }
        option_two.setOnClickListener { OnClickOptionTwo() }
        option_three.setOnClickListener { OnClickOptionThree() }
        when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> { // Note the block
        print("x no es 1 o 2")
    }
}
        */
        containerAnimals.setOnClickListener { printImages() }
        img_return.setOnClickListener { finish() }
    }


    private fun matchImageWithNumbers() : HashMap<Int, HashMap<String, Int>> {
        val rootHashmap = HashMap<Int, HashMap<String, Int>>()
        var ImagesItems = HashMap<String, Int>()

        ImagesItems["number"] = R.drawable.number_one_recognize
        ImagesItems["animal"] =  R.drawable.number_one_recognize
        rootHashmap[1] = ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_two_recognize
        ImagesItems["animal"] =  R.drawable.number_two_recognize
        rootHashmap[2] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_three_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[3] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_four_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[4] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_five_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[5] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_six_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[6] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_seven_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[7] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_eight_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[8] =  ImagesItems
        ImagesItems = HashMap()
        ImagesItems["number"] = R.drawable.number_nine_recognize
        ImagesItems["animal"] =  R.drawable.animal_giraffe
        rootHashmap[9] =  ImagesItems
        return rootHashmap
    }

    private fun printImages() {
        var randomNumbers = getRandomNumbers()
        var correctOption = getCorrectOption(randomNumbers)
        var positioncorrectOption = randomNumbers.indexOf(correctOption)
        var imagesToPrint = matchImageWithNumbers()

        printCorrectNumberOption(positioncorrectOption, imagesToPrint[correctOption]?.get("number")!!)
        animal_seven.setImageResource(imagesToPrint[correctOption]?.get("animal")!!)

        printIncorrectNumberOptions(positioncorrectOption, imagesToPrint, randomNumbers)
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

    private fun printCorrectAnimalOption(selectNumberToPrintNumber: Int, imageNumber : Int) {
        when (selectNumberToPrintNumber) {
            0 -> img_number_left.setImageResource(imageNumber)
            1 -> img_number_center.setImageResource(imageNumber)
            2 -> img_number_right.setImageResource(imageNumber)
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

    private fun selectPositionToPrintNumber() : Int {
        return (0..2).random()
    }

    private fun getCorrectOption(randomList: List<Int>) : Int {
        return randomList[(0..2).random()]
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
}
