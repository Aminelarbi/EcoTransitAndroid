package com.example.ecotansit.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecotansit.Constants
import com.example.ecotansit.R
import com.example.ecotansit.models.Subscribe

class SubscribeAdapter : RecyclerView.Adapter<SubscribeAdapter.SubscribeViewHolder>() {
    var mySubscribes: List<Subscribe> = ArrayList()

    fun setSubscribes(subscribes: List<Subscribe>) {
        this.mySubscribes = subscribes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_subscribe, parent, false)
        return SubscribeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscribeViewHolder, position: Int) {
        val subscribe = mySubscribes[position]
        holder.bind(subscribe)
    }

    override fun getItemCount(): Int {
        return mySubscribes.size
    }

    inner class SubscribeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.fullnameTextView)
        private val estimatedPriceTextView: TextView = itemView.findViewById(R.id.estimatedPriceTextView)
        private val estimatedTimeTextView: TextView = itemView.findViewById(R.id.estimatedTimeTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //listener.onItemClick(mySubscribes[position])
                }
            }
        }

        fun bind(subscribe: Subscribe) {
            nameTextView.text = subscribe.name
            estimatedPriceTextView.text ="Estimated Price: ${subscribe.price.toString()}"
            estimatedTimeTextView.text = subscribe.startDateString + " - " + subscribe.endDateString
            val imageUrl = subscribe.imageName

            Log.d("image", "$imageUrl")
            // Assuming imageNameTextView is an ImageView, load the image into it using Glide
            Glide.with(itemView.context).load(Constants.IMAGE_URL + imageUrl).into(imageView)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(subscribe: Subscribe)
    }
}
