package co.com.zoomathias.zoomathias.businesslogic

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.zoomathias.zoomathias.R
import co.com.zoomathias.zoomathias.models.Recognize

class RecognizeBrain {

    private var resourcesImages = resourcesToPrint()

    fun showNumber(viewNumber: ImageView, position: Int) {
        viewNumber.setImageResource(resourcesImages[position].number)
    }

    fun showAnimal(viewAnimal: ImageView, animalName: TextView, position: Int) {
        viewAnimal.setImageResource(resourcesImages[position].animal)
        animalName.text = resourcesImages[position].animalName
    }

    fun showHands(viewHandOne: ImageView, viewHandTwo: ImageView, position: Int) {
        viewHandOne.setImageResource(resourcesImages[position].hand[0])
        if (position > 4) {
            viewHandTwo.visibility = View.VISIBLE
            viewHandTwo.setImageResource(resourcesImages[position].hand[1])
        } else {
            viewHandTwo.visibility = View.GONE
        }
    }

    fun getResourceCount(): Int {
        return resourcesImages.size
    }

    private fun resourcesToPrint(): ArrayList<Recognize> {
        val resources: ArrayList<Recognize> = ArrayList()
        var hands: ArrayList<Int> = ArrayList()
        hands.add(R.drawable.hand_number_one)
        resources.add(Recognize(R.drawable.number_one, hands, R.drawable.animal_r_unicorn, "Unicornio"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_two)
        resources.add(Recognize(R.drawable.number_two, hands, R.drawable.animal_r_dolphin, "Delfin"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_three)
        resources.add(Recognize(R.drawable.number_three, hands, R.drawable.animal_r_shark, "Tiburón"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_four)
        resources.add(Recognize(R.drawable.number_four, hands, R.drawable.animal_r_kangaroo, "Canguro"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_five)
        resources.add(Recognize(R.drawable.number_five, hands, R.drawable.animal_r_camel, "Camello"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_five)
        hands.add(R.drawable.hand_number_one)
        resources.add(Recognize(R.drawable.number_six, hands, R.drawable.animal_r_snake, "Serpiente"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_five)
        hands.add(R.drawable.hand_number_two)
        resources.add(Recognize(R.drawable.number_seven, hands, R.drawable.animal_r_toad, "Sapo"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_five)
        hands.add(R.drawable.hand_number_three)
        resources.add(Recognize(R.drawable.number_eight, hands, R.drawable.animal_r_bear, "Oso"))
        hands = ArrayList()
        hands.add(R.drawable.hand_number_five)
        hands.add(R.drawable.hand_number_four)
        resources.add(Recognize(R.drawable.number_nine, hands, R.drawable.animal_r_otter, "Nutria"))

        return resources
    }
}