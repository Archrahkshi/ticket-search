package com.archrahkshi.ticketsearch.ui.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.databinding.FragmentSearchBinding
import com.archrahkshi.ticketsearch.ui.BaseFragment
import com.archrahkshi.ticketsearch.ui.DEPARTURE_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.DESTINATION_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.applyPriceTemplate
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModel()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(views) {
            // Setup directions
            departureText.text = arguments?.getString(DEPARTURE_TEXT_KEY, "")
            destinationTextField.setText(arguments?.getString(DESTINATION_TEXT_KEY, ""))
            changeDirectionIcon.setOnClickListener {
                val temp = departureText.text
                departureText.text = destinationTextField.text
                destinationTextField.setText(temp)
            }

            // Setup search parameters
            departureDate.icon.isVisible = false
            viewModel.selectedDepartureDate.observe(viewLifecycleOwner) {
                departureDate.title.text = getColoredDate(it)
            }
            departureDate.container.setOnClickListener { view ->
                chooseDate(view)
            }
            returnDate.icon.setImageResource(R.drawable.plus)
            returnDate.title.text = viewModel.selectedReturnDate.value?.ifEmpty {
                getString(R.string.back)
            }
            viewModel.selectedReturnDate.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    returnDate.title.text = getColoredDate(it)
                }
            }
            returnDate.container.setOnClickListener { view ->
                chooseDate(view, true)
            }
            returnDate.container.setOnLongClickListener {
                viewModel.unsetReturnDate(getString(R.string.back))
                true
            }
            passengers.icon.setImageResource(R.drawable.passenger)
            passengers.title.text =
                getString(R.string.passenger_placeholder, getString(R.string.economy))
            filters.icon.setImageResource(R.drawable.filter)
            filters.title.text = getString(R.string.filters)

            // Setup ticket offers
            airline1.logo.setImageResource(R.drawable.airline_logo_red)
            airline2.logo.setImageResource(R.drawable.airline_logo_blue)
            airline3.logo.setImageResource(R.drawable.airline_logo_white)
            lifecycleScope.launch {
                val ticketOffers = viewModel.getTicketOffers()
                airline1.title.text = ticketOffers[0].title
                airline1.price.text = applyPriceTemplate(view.context, ticketOffers[0].price)
                airline1.departures.text = ticketOffers[0].timeRange.joinToString(" ")
                airline2.title.text = ticketOffers[1].title
                airline2.price.text = applyPriceTemplate(view.context, ticketOffers[1].price)
                airline2.departures.text = ticketOffers[1].timeRange.joinToString(" ")
                airline3.title.text = ticketOffers[2].title
                airline3.price.text = applyPriceTemplate(view.context, ticketOffers[2].price)
                airline3.departures.text = ticketOffers[2].timeRange.joinToString(" ")
            }
        }
    }

    private fun chooseDate(view: View, isReturnDate: Boolean = false) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            view.context,
            { _, year, month, day ->
                calendar.set(year, month, day)
                if (isReturnDate) {
                    viewModel.updateReturnDate(calendar.time)
                } else {
                    viewModel.updateDate(calendar.time)
                }
            },
            calendar[YEAR],
            calendar[MONTH],
            calendar[DAY_OF_MONTH]
        ).show()
    }

    private fun getColoredDate(text: String) = SpannableString(text).apply {
        setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.grey_6)),
            text.indexOf(','),
            text.length,
            0
        )
    }.toString()
}
