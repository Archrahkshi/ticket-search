package com.archrahkshi.ticketsearch.ui.start

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.ticketsearch.data.vo.FlattenedOffer
import com.archrahkshi.ticketsearch.databinding.ItemConcertBinding
import com.archrahkshi.ticketsearch.ui.applyPriceTemplate

class ConcertsAdapter(
    private val concerts: List<FlattenedOffer>,
    private val images: List<Bitmap>
) : RecyclerView.Adapter<ConcertsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemConcertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(concerts[position], images[position])
    }

    override fun getItemCount() = concerts.size

    inner class ViewHolder(
        private val views: ItemConcertBinding
    ) : RecyclerView.ViewHolder(views.root) {
        fun bind(concert: FlattenedOffer, image: Bitmap) = with(views) {
            this.image.setImageBitmap(image)
            title.text = concert.title
            town.text = concert.town
            price.text = applyPriceTemplate(price.context, concert.price)
        }
    }
}
