package br.com.mobile.marvelcharacters.data.di

import br.com.mobile.marvelcharacters.data.network.MarvelCharactersDetailDataSource
import br.com.mobile.marvelcharacters.data.repository.MarvelCharacterRepositoryImpl
import br.com.mobile.marvelcharacters.data.service.ApiInstanceProvider
import br.com.mobile.marvelcharacters.domain.repository.MarvelCharacterRepository
import org.koin.dsl.module

val dataModule = module {
    single { ApiInstanceProvider.marvelCharactersApi }
    single { MarvelCharactersDetailDataSource(get()) }
    single<MarvelCharacterRepository> { MarvelCharacterRepositoryImpl(get(), get()) }
}

