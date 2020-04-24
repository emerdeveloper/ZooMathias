package co.com.zoomathias.zoomathias.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.zoomathias.zoomathias.R
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.shop_item.view.*

class CharacterRecyclerViewAdapter (val items : ArrayList<String>, val clickListener: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CharacterRecyclerViewHolderItem {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.shop_item, parent, false)
        return CharacterRecyclerViewHolderItem(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        (holder as CharacterRecyclerViewHolderItem).bind(items[position], clickListener)
    }

    //ViewHolder
    class CharacterRecyclerViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(characterImage: String, clickListener: (String) -> Unit) {
            itemView.charcter_framework.setAnimation(characterImage)
            itemView.charcter_framework.repeatCount = LottieDrawable.INFINITE
            itemView.charcter_framework.playAnimation()
            itemView.setOnClickListener { clickListener(characterImage) }
        }
    }
}