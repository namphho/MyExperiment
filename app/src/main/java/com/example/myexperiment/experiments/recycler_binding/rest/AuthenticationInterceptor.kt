package com.example.myexperiment.experiments.recycler_binding.rest

import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * Created by nampham on 5/7/21.
 */
class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", "7519cb3f829ecd53bd9b7007076dbe23")
            .build()
        Log.e("Authentication", "${url.queryParameterNames()}")

        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}