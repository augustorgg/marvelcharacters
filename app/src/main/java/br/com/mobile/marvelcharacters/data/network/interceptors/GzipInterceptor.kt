package br.com.mobile.marvelcharacters.data.network.interceptors

import java.util.zip.GZIPInputStream
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class GzipInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val body = originalResponse.body()

        return if (body != null && originalResponse.header("Content-Encoding") == "gzip") {
            val gzippedInputStream = GZIPInputStream(body.byteStream())
            val unzippedResponseBody = gzippedInputStream.bufferedReader().use { it.readText() }
            val contentType = body.contentType()
            val contentLength = body.contentLength()
            val unzippedBody = ResponseBody.create(contentType, unzippedResponseBody)
            originalResponse.newBuilder().body(unzippedBody)
                .header("Content-Length", contentLength.toString()).build()
        } else {
            originalResponse
        }
    }
}
