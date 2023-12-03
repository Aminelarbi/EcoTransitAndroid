package com.example.ecotansit.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecotansit.Constants
import com.example.ecotansit.MainActivity
import com.example.ecotansit.R
import com.example.ecotansit.models.Billet
import com.example.ecotansit.view.subscribeActivity

class BilletAdapter() : RecyclerView.Adapter<BilletAdapter.BilletViewHolder>() {
    var myBillets: List<Billet> = ArrayList()

    fun setBillets(billets: List<Billet>) {
        this.myBillets = billets
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BilletViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_billet, parent, false)
        return BilletViewHolder(view)
    }

    override fun onBindViewHolder(holder: BilletViewHolder, position: Int) {
        val billet = myBillets[position]
        holder.bind(billet)
    }

    override fun getItemCount(): Int {
        return myBillets.size
    }

    inner class BilletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val distanceTextView: TextView = itemView.findViewById(R.id.distanceTextView)
        private val estimatedPriceTextView: TextView = itemView.findViewById(R.id.estimatedPriceTextView)
        private val estimatedTimeTextView: TextView = itemView.findViewById(R.id.estimatedTimeTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //listener.onItemClick(myBillets[position])
                }
                val i = Intent(it.context, subscribeActivity::class.java)
                it.context.startActivity(i)
                Toast.makeText(it.context,"Hello", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(billet: Billet) {
            distanceTextView.text = "Distance: ${billet.distance.toString()}"
            estimatedPriceTextView.text ="Estimated Price: ${billet.estimatedPrice.toString()}"
            estimatedTimeTextView.text = billet.estimatedTime
            val imageUrl = billet.imageName

            Log.d("image", "$imageUrl")
            // Assuming imageNameTextView is an ImageView, load the image into it using Glide
            Glide.with(itemView.context).load(Constants.IMAGE_URL +imageUrl).into(imageView)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(billet: Billet)
    }
}
