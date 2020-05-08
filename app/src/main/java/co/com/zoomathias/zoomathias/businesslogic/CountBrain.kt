package co.com.zoomathias.zoomathias.businesslogic

import android.view.View
import android.widget.ImageView
import co.com.zoomathias.zoomathias.R
import co.com.zoomathias.zoomathias.utils.Constants
import kotlin.random.Random

class CountBrain {

    private var images: HashMap<Int, HashMap<String, Int>>?
    private var correctOption: Int = 0
    private var positionCorrectOption: Int = 0
    private var score : Int = 0
    private var attempts: Int = 0

    init {
        images = matchImageWithNumbers()
    }

    fun isFinishAttempts(): Boolean {
        return  (attempts == Constants.ATTEMPTS)
    }

    fun isWinner(): Boolean {
        return  (score >= Constants.POINTS_TO_WIN)
    }

    fun isValidAnswers(positionSelected : Int): Boolean{
        attempts++
        return if (positionCorrectOption == positionSelected) {
            score++
            true
        } else {
            false
        }
    }

    fun showImages(imageList: List<ImageView>, animalImages: List<ImageView>) {
        val randomNumbers = getRandomNumbers()
        correctOption = randomNumbers[(0..2).random()]
        positionCorrectOption = randomNumbers.indexOf(correctOption)

        printCorrectNumberOption(imageList)
        printIncorrectNumberOptions(randomNumbers, imageList)
        printCorrectAnimalOption(animalImages)
    }

    private fun printCorrectAnimalOption(animalImages: List<ImageView>) {
        for (x in animalImages.indices) {
            if (x < (correctOption)) {
                if (animalImages[x].visibility == View.GONE)
                {
                    animalImages[x].visibility = View.VISIBLE
                }
                animalImages[x].setImageResource(images!![correctOption]?.get("animal")!!)
            }
            else {
                animalImages[x].visibility = View.GONE
            }
        }
    }

     private fun printIncorrectNumberOptions(incorrectOptions: List<Int>, imageList: List<ImageView>) {
        when (positionCorrectOption) {
            0 -> {
                imageList[1].setImageResource(images!![incorrectOptions[1]]?.get("number")!!)
                imageList[2].setImageResource(images!![incorrectOptions[2]]?.get("number")!!)
            }
            1 -> {
                imageList[0].setImageResource(images!![incorrectOptions[2]]?.get("number")!!)
                imageList[2].setImageResource(images!![incorrectOptions[0]]?.get("number")!!)
            }
            2 -> {
                imageList[1].setImageResource(images!![incorrectOptions[1]]?.get("number")!!)
                imageList[0].setImageResource(images!![incorrectOptions[0]]?.get("number")!!)
            }
        }
    }

    private fun printCorrectNumberOption(imageList: List<ImageView>) {
        //img_number_left.setImageResource(images["number"]!!)
        when (positionCorrectOption) {
            0 -> imageList[0].setImageResource(images!![correctOption]?.get("number")!!)
            1 -> imageList[1].setImageResource(images!![correctOption]?.get("number")!!)
            2 -> imageList[2].setImageResource(images!![correctOption]?.get("number")!!)
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
}