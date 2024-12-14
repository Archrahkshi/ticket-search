package com.archrahkshi.ticketsearch.ui.tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.ticketsearch.data.vo.SimplifiedTicket
import com.archrahkshi.ticketsearch.databinding.ItemTicketBinding
import com.archrahkshi.ticketsearch.ui.applyPriceTemplate

class TicketsAdapter(
    private val tickets: List<SimplifiedTicket>
) : RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TicketsViewHolder(
        ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(tickets[position])
    }

    override fun getItemCount() = tickets.size

    class TicketsViewHolder(
        private val views: ItemTicketBinding
    ) : RecyclerView.ViewHolder(views.root) {
        fun bind(ticket: SimplifiedTicket) = with(views) {
            ticket.badge?.let {
                (container.layoutParams as ConstraintLayout.LayoutParams).topMargin =
                    (8 * container.resources.displayMetrics.density).toInt()
                badge.isVisible = true
                badge.text = ticket.badge
            }
            price.text = applyPriceTemplate(price.context, ticket.price)
            departureTime.text = ticket.departureTime
            departureAirport.text = ticket.departureAirport
            arrivalTime.text = ticket.arrivalTime
            arrivalAirport.text = ticket.arrivalAirport
            duration.text = ticket.duration
            if (!ticket.hasTransfer) {
                slash.isVisible = true
                noTransfer.isVisible = true
            }
        }
    }
}
