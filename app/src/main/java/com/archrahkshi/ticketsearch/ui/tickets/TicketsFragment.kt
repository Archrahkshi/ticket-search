package com.archrahkshi.ticketsearch.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.databinding.FragmentTicketsBinding
import com.archrahkshi.ticketsearch.ui.BaseFragment
import com.archrahkshi.ticketsearch.ui.DEPARTURE_DATE_KEY
import com.archrahkshi.ticketsearch.ui.DEPARTURE_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.DESTINATION_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.MainActivity
import com.archrahkshi.ticketsearch.ui.PASSENGER_COUNT_KEY
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : BaseFragment<FragmentTicketsBinding>() {
    private val viewModel by viewModel<TicketsViewModel>()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentTicketsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showLoading()
        views.backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        views.fromTo.text = buildString {
            append(arguments?.getString(DEPARTURE_TEXT_KEY, ""))
            append(" ${getString(R.string.dash)} ")
            append(arguments?.getString(DESTINATION_TEXT_KEY) ?: "")
        }
        views.parameters.text = buildString {
            append(arguments?.getString(DEPARTURE_DATE_KEY, ""))
            append(", ")
            val passengerCount = arguments?.getInt(PASSENGER_COUNT_KEY) ?: 1
            append(resources.getQuantityString(R.plurals.passengers, passengerCount, passengerCount))
        }
        with(views.tickets) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, VERTICAL).apply {
                    getDrawable(context, R.drawable.ticket_list_divider)?.let(::setDrawable)
                }
            )
            lifecycleScope.launch {
                adapter = TicketsAdapter(viewModel.getTickets())
                (requireActivity() as MainActivity).hideLoading()
            }
        }
    }
}
