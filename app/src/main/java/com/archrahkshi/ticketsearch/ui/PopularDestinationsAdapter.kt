package com.archrahkshi.ticketsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.data.Destination

class PopularDestinationsAdapter(
    private val popularDestinations: List<Destination>,
    private val destinationTextField: EditText
) : RecyclerView.Adapter<PopularDestinationsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular_destination, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageBitmap(popularDestinations[position].image)
        val title = popularDestinations[position].title
        holder.title.text = title
        holder.subtitle.text = popularDestinations[position].subtitle
        holder.container.setOnClickListener {
            destinationTextField.setText(title)
        }
    }

    override fun getItemCount() = popularDestinations.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val container: ConstraintLayout = itemView.findViewById(R.id.popular_destination_container)
    }
}
