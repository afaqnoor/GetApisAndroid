package com.example.apis

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter (val context : Activity, val productArrayList: List<Product>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  productArrayList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        holder.des.text = currentItem.description
        // imageView , how to show image in imageView if image is url form , so we use 3rd Part Library
        //picasso i use
        Picasso.get().load(currentItem.thumbnail).into(holder.image);
        val rating = currentItem.rating
        val ratingImageResource = getRatingImageResource(rating)
        holder.rating.setImageResource(ratingImageResource)

    }

    private fun getRatingImageResource(rating: Double): Int {
        // Determine which star image to use based on the rating value
        // You can customize this logic based on your star drawables.
        // For example, if you have 5 stars, you can map ratings 0-0.5 to "star_empty",
        // 0.5-1.0 to "star_half", and 1.0-5.0 to "star_full".

        if (rating <= 4.1) {
            return R.drawable.empty_star
        } else if (rating <= 4.5) {
            return R.drawable.half_star
        } else {
            return R.drawable.full_star
        }
    }

    // we can create a MyVieHolder function mean that the handle of Ui which can we provide a data on user
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var title : TextView
        var rating : ImageView
        var image : ShapeableImageView
        var des : TextView

        init {
            title = itemView.findViewById(R.id.productName)
            des = itemView.findViewById(R.id.productDes)
            rating = itemView.findViewById(R.id.ratImageView)
            image = itemView.findViewById(R.id.productImage)
        }
    }

}