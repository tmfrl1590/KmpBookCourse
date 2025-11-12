package com.kmp.book.di

import com.kmp.book.data.HttpClientFactory
import com.kmp.book.data.repository.BookRepositoryImpl
import com.kmp.book.domain.repository.BookRepository
import com.kmp.book.presentation.screen.home.viewmodel.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::BookRepositoryImpl).bind<BookRepository>()

    viewModelOf(::HomeViewModel)
}