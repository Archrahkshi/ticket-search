package com.archrahkshi.ticketsearch.ui.start

import androidx.lifecycle.ViewModel
import com.archrahkshi.ticketsearch.domain.repository.OffersRepository

class StartViewModel(private val offersRepository: OffersRepository) : ViewModel() {
    suspend fun getOffers() = offersRepository.getOffers()
}
