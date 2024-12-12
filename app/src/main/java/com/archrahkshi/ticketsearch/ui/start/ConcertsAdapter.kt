package com.archrahkshi.ticketsearch.ui.start

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.data.vo.FlattenedOffer

class ConcertsAdapter(
    private val concerts: List<FlattenedOffer>,
    private val images: List<Bitmap>,
    private val priceStringTemplate: String
) : RecyclerView.Adapter<ConcertsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_concert, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            image.setImageBitmap(images[position])
            title.text = concerts[position].title
            location.text = concerts[position].town
            cost.text =
                priceStringTemplate.replace("{price}", concerts[position].price.toString())
        }
    }

    override fun getItemCount() = concerts.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.concert_image)
        val title: TextView = view.findViewById(R.id.concert_title)
        val location: TextView = view.findViewById(R.id.concert_location)
        val cost: TextView = view.findViewById(R.id.concert_flight_cost)
    }
}
