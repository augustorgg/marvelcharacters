package br.com.mobile.marvelcharacters.presentation.di

import br.com.mobile.marvelcharacters.presentation.viewmodel.MarvelCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MarvelCharactersViewModel(get()) }
}
