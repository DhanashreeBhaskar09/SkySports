package com.example.master.di

import com.example.master.activities.news.headlines.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { NewsViewModel(get()) }
}