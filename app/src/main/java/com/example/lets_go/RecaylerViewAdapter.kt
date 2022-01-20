package com.example.lets_go

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecalerViewAdapter(private var list: List<Profile>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<RecalerViewAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

         val imageView: ImageView
         val textView: TextView
         val textView2: TextView

         val item: ConstraintLayout = itemView.findViewById(R.id.item)

         init {
             imageView = itemView.findViewById(R.id.imageView)
             textView = itemView.findViewById(R.id.textView)
             textView2 = itemView.findViewById(R.id.textView2)

             item.setOnClickListener(this)

         }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun setData(person: Profile) {
            textView.text = person.title
            textView2.text = person.name

            Glide.with(imageView)
                .load(person.imageUrl)
                .into(imageView)
                .view
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        val person = list[position]
        holder.setData(person)

    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}