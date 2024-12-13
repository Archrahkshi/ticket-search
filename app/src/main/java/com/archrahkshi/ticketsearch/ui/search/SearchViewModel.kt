package com.archrahkshi.ticketsearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.archrahkshi.ticketsearch.domain.repository.TicketOffersRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SearchViewModel(private val ticketOffersRepository: TicketOffersRepository) : ViewModel() {
    private val _selectedDate = MutableLiveData(formatDate(Date()))
    val selectedDepartureDate: LiveData<String> get() = _selectedDate

    private val _selectedReturnDate = MutableLiveData("")
    val selectedReturnDate: LiveData<String> get() = _selectedReturnDate

    private fun formatDate(date: Date): String =
        SimpleDateFormat("dd MMM, E", Locale("ru"))
            .format(date)
            .replace(".", "")

    fun updateDate(date: Date) {
        _selectedDate.value = formatDate(date)
    }

    fun updateReturnDate(date: Date) {
        _selectedReturnDate.value = formatDate(date)
    }

    fun unsetReturnDate(text: String) {
        _selectedReturnDate.value = text
    }

    suspend fun getTicketOffers() = ticketOffersRepository.getTicketOffers()
}
