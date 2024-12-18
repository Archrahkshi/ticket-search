package com.archrahkshi.ticketsearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.archrahkshi.ticketsearch.domain.repository.TicketOffersRepository
import java.util.Date

class SearchViewModel(private val ticketOffersRepository: TicketOffersRepository) : ViewModel() {
    private val _selectedDepartureDate = MutableLiveData(Date())
    val selectedDepartureDate: LiveData<Date> get() = _selectedDepartureDate

    private val _selectedReturnDate = MutableLiveData<Date?>()
    val selectedReturnDate: LiveData<Date?> get() = _selectedReturnDate

    fun updateDate(date: Date) {
        _selectedDepartureDate.value = date
    }

    fun updateReturnDate(date: Date) {
        _selectedReturnDate.value = date
    }

    fun unsetReturnDate() {
        _selectedReturnDate.value = null
    }

    suspend fun getTicketOffers() = ticketOffersRepository.getTicketOffers()
}
