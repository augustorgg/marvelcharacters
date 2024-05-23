package br.com.mobile.marvelcharacters.data.network.interceptors

import br.com.mobile.marvelcharacters.BuildConfig.API_PRIVATE_KEY
import br.com.mobile.marvelcharacters.BuildConfig.API_PUBLIC_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.Date

class AuthInterceptor : Interceptor {
    private val publicKey: String = API_PUBLIC_KEY
    private val privateKey: String = API_PRIVATE_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val ts = Date().time.toString()
        val hash = md5("$ts$privateKey$publicKey")

        val url =
            originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", hash)
                .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}
