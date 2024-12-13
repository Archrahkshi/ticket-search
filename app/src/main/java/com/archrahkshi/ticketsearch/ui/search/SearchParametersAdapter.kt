package com.archrahkshi.ticketsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.ticketsearch.databinding.ItemSearchParameterBinding

class SearchParametersAdapter(
    private val parameters: List<SearchParameter>
) : RecyclerView.Adapter<SearchParametersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSearchParameterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(parameters[position])
    }

    override fun getItemCount() = parameters.size

    class ViewHolder(
        private val views: ItemSearchParameterBinding
    ) : RecyclerView.ViewHolder(views.root) {
        fun bind(parameter: SearchParameter) {
            with(views) {
                parameter.icon?.let(icon::setImageResource)
                title.text = parameter.title
                container.setOnClickListener {
                    parameter.onClick?.let { it1 -> it1() }
                }
            }
        }
    }
}
