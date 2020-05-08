package co.com.zoomathias.zoomathias.businesslogic

import android.view.View
import android.widget.ImageView
import co.com.zoomathias.zoomathias.R
import co.com.zoomathias.zoomathias.models.Character

class CharactersShopBrains (
    private val userStars: Int
) {
    private var characters: ArrayList<Character>?
    private var characterSelected: Int = 0
    private var characterImage: String = ""

    init {
        characters = addCharacters()
    }

    fun boughtCharacter(): Boolean {
        return !characterImage.isBlank() && isCharacterAvailableToBuy()
    }

    fun isCharacterAvailableToBuy(): Boolean {
        return (userStars >= getCharacterStarRating())
    }

    fun characterSelected(characterSelected: Int) {
        this.characterSelected = characterSelected
        characterImage = getCharacterImage()
    }

    fun getCharacterImage(): String {
        return characters!![characterSelected].nameFile
    }

    fun getCharacterStarRating(): Int {
        return characters!![characterSelected].starRating
    }

    fun showCharacterStars(listStarImages: List<ImageView>) {
        if (userStars < getCharacterStarRating()) {
            for (x in (userStars) until getCharacterStarRating()) {
                listStarImages[x].setImageResource(R.drawable.img_star_disabled)
                listStarImages[x].visibility = View.VISIBLE
            }
        }
        else if (userStars >= getCharacterStarRating()) {
            for (x in (userStars) until listStarImages.size) {
                if (listStarImages[x].visibility == View.VISIBLE) {
                    listStarImages[x].visibility = View.GONE
                }
            }
        }
    }

    fun showUserStars(listStarImages: List<ImageView>) {
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

    fun getCharacters()  : ArrayList<Character>? {
        return characters
    }

    private fun addCharacters() : ArrayList<Character> {
        val characters: ArrayList<Character> = ArrayList()
        characters.add(Character("chicken.json", 8))
        characters.add(Character("mouse.json", 6))
        characters.add(Character("cat.json", 6))
        characters.add(Character("pigeon.json", 4))
        characters.add(Character("bee.json", 5))
        return characters
    }
}