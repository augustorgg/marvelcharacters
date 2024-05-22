package br.com.mobile.marvelcharacters

import android.app.Application
import br.com.mobile.marvelcharacters.data.di.dataModule
import br.com.mobile.marvelcharacters.domain.di.domainModule
import br.com.mobile.marvelcharacters.presentation.di.presentationModule
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MarvelCharactersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelCharactersApplication)
            modules(listOf(dataModule, domainModule, presentationModule))
        }

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this))
        val picasso = builder.build()
        Picasso.setSingletonInstance(picasso)
    }
}
