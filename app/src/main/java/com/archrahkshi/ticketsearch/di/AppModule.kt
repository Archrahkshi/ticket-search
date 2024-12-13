package com.archrahkshi.ticketsearch.di

import com.archrahkshi.ticketsearch.data.repositoryimpl.OffersRepositoryImpl
import com.archrahkshi.ticketsearch.data.repositoryimpl.TicketOffersRepositoryImpl
import com.archrahkshi.ticketsearch.domain.repository.OffersRepository
import com.archrahkshi.ticketsearch.domain.repository.TicketOffersRepository
import com.archrahkshi.ticketsearch.ui.search.SearchViewModel
import com.archrahkshi.ticketsearch.ui.start.StartViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::OffersRepositoryImpl) { bind<OffersRepository>() }
    singleOf(::TicketOffersRepositoryImpl) { bind<TicketOffersRepository>() }
    viewModelOf(::StartViewModel)
    viewModelOf(::SearchViewModel)
}
