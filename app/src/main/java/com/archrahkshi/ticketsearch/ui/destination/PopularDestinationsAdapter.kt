package com.archrahkshi.ticketsearch.ui.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.ticketsearch.data.vo.Destination
import com.archrahkshi.ticketsearch.databinding.ItemPopularDestinationBinding

class PopularDestinationsAdapter(
    private val popularDestinations: List<Destination>,
) : RecyclerView.Adapter<PopularDestinationsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemPopularDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(popularDestinations[position])
    }

    override fun getItemCount() = popularDestinations.size

    class ViewHolder(
        private val views: ItemPopularDestinationBinding
    ) : RecyclerView.ViewHolder(views.root) {
        fun bind(destination: Destination) = with(views) {
            image.setImageBitmap(destination.image)
            title.text = destination.title
            subtitle.text = destination.subtitle
            container.setOnClickListener {
                destination.onClick(destination.title)
            }
        }
    }
}
