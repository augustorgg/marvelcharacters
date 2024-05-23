package br.com.mobile.marvelcharacters.domain.di

import br.com.mobile.marvelcharacters.domain.usecase.GetMarvelCharacterUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule =
    module {
        factory { Dispatchers.IO }
        single { GetMarvelCharacterUseCase(get(), get()) }
    }
