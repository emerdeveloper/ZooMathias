package co.com.zoomathias.zoomathias.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.zoomathias.zoomathias.R
import co.com.zoomathias.zoomathias.models.Character
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.shop_item.view.*

class CharacterRecyclerViewAdapter (val items : ArrayList<Character>?, val clickListener: (Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CharacterRecyclerViewHolderItem {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.shop_item, parent, false)
        return CharacterRecyclerViewHolderItem(view)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        (holder as CharacterRecyclerViewHolderItem).bind(items!![position], position, clickListener)
    }

    //ViewHolder
    class CharacterRecyclerViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(character: Character, position: Int, clickListener: (Int) -> Unit) {
            itemView.charcter_framework.setAnimation(character.nameFile)
            itemView.charcter_framework.repeatCount = LottieDrawable.INFINITE
            itemView.img_number.setImageResource(character.resourceImageNumber)
            itemView.charcter_framework.playAnimation()
            itemView.setOnClickListener { clickListener(position) }
        }
    }
}