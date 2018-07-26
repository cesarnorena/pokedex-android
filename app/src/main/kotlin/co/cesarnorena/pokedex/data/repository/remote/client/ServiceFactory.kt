package co.cesarnorena.pokedex.data.repository.remote.client

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> createRemoteService(apiUrl: String): T {
    val httpClientBuilder = OkHttpClient.Builder()

    return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()
            .create(T::class.java)
}
