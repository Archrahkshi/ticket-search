package com.archrahkshi.ticketsearch.ui.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.bundle.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.archrahkshi.ticketsearch.R
import com.archrahkshi.ticketsearch.databinding.FragmentSearchBinding
import com.archrahkshi.ticketsearch.getDefaultLocale
import com.archrahkshi.ticketsearch.ui.BaseFragment
import com.archrahkshi.ticketsearch.ui.DEPARTURE_DATE_KEY
import com.archrahkshi.ticketsearch.ui.DEPARTURE_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.DESTINATION_TEXT_KEY
import com.archrahkshi.ticketsearch.ui.MainActivity
import com.archrahkshi.ticketsearch.ui.PASSENGER_COUNT_KEY
import com.archrahkshi.ticketsearch.ui.applyPriceTemplate
import com.archrahkshi.ticketsearch.ui.tickets.TicketsFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR
import java.util.Date

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel by viewModel<SearchViewModel>()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showLoading()
        with(views) {
            // Setup directions
            backArrow.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            departureText.text = arguments?.getString(DEPARTURE_TEXT_KEY, "")
            destinationTextField.setText(arguments?.getString(DESTINATION_TEXT_KEY, ""))
            destinationTextField.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    updateTickets()
                }
            }
            changeDirectionIcon.setOnClickListener {
                val temp = departureText.text
                departureText.text = destinationTextField.text
                destinationTextField.setText(temp)
                updateTickets()
            }
            clearIcon.setOnClickListener {
                destinationTextField.text.clear()
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
            returnDate.title.text = viewModel.selectedReturnDate.value?.let(::getColoredDate)
                ?: getString(R.string.back)
            viewModel.selectedReturnDate.observe(viewLifecycleOwner) {
                returnDate.title.text = it?.let(::getColoredDate) ?: getString(R.string.back)
            }
            returnDate.container.setOnClickListener { view ->
                chooseDate(view, true)
            }
            returnDate.container.setOnLongClickListener {
                viewModel.unsetReturnDate()
                true
            }
            passengers.icon.setImageResource(R.drawable.passenger)
            val passengerCount = 1
            passengers.title.text = getString(
                R.string.passenger_placeholder,
                passengerCount,
                getString(R.string.economy)
            )
            filters.icon.setImageResource(R.drawable.filter)
            filters.title.text = getString(R.string.filters)

            // Setup ticket offers
            updateTickets()
            viewAllTickets.setOnClickListener {
                parentFragmentManager.commit {
                    replace(
                        R.id.fragment_container,
                        TicketsFragment::class.java,
                        bundleOf(
                            DEPARTURE_TEXT_KEY to views.departureText.text,
                            DESTINATION_TEXT_KEY to views.destinationTextField.text.toString(),
                            DEPARTURE_DATE_KEY to getLongDate(viewModel.selectedDepartureDate.value),
                            PASSENGER_COUNT_KEY to passengerCount
                        )
                    )
                    addToBackStack(null)
                }
            }
        }
    }

    private fun updateTickets() {
        with(views) {
            airline1.logo.setImageResource(R.drawable.airline_logo_red)
            airline2.logo.setImageResource(R.drawable.airline_logo_blue)
            airline3.logo.setImageResource(R.drawable.airline_logo_white)
            lifecycleScope.launch {
                val ticketOffers = viewModel.getTicketOffers()
                airline1.title.text = ticketOffers[0].title
                airline1.price.text = applyPriceTemplate(requireContext(), ticketOffers[0].price)
                airline1.departures.text = ticketOffers[0].timeRange.joinToString(" ")
                airline2.title.text = ticketOffers[1].title
                airline2.price.text = applyPriceTemplate(requireContext(), ticketOffers[1].price)
                airline2.departures.text = ticketOffers[1].timeRange.joinToString(" ")
                airline3.title.text = ticketOffers[2].title
                airline3.price.text = applyPriceTemplate(requireContext(), ticketOffers[2].price)
                airline3.departures.text = ticketOffers[2].timeRange.joinToString(" ")
            }
        }
        (requireActivity() as? MainActivity)?.hideLoading()
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

    private fun getColoredDate(date: Date) =
        SimpleDateFormat("dd MMM, E", getDefaultLocale())
            .format(date)
            .replace(".", "")
            .let {
                SpannableString(it).apply {
                    setSpan(
                        ForegroundColorSpan(requireContext().getColor(R.color.grey_6)),
                        it.indexOf(','),
                        it.length,
                        0
                    )
                }.toString()
            }

    private fun getLongDate(date: Date?) =
        date?.let { SimpleDateFormat("dd MMMM", getDefaultLocale()).format(it) }.orEmpty()
}
