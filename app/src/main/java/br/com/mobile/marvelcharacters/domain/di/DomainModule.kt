package br.com.mobile.marvelcharacters.domain.di

import br.com.mobile.marvelcharacters.domain.usecase.GetMarvelCharacterUseCase
import br.com.mobile.marvelcharacters.domain.usecase.GetMarvelCharacterUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule =
    module {
        factory { Dispatchers.IO }
        single<GetMarvelCharacterUseCase> { GetMarvelCharacterUseCaseImpl(get(), get()) }
    }
