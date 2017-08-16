package co.cesarnorena.pokedex.data.remote.client

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceFactory {

    companion object {

        fun <T> create(clazz: Class<T>, endPoint: String): T {
            val httpClientBuilder = OkHttpClient.Builder()

            val retrofit = Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build()
            return retrofit.create(clazz)
        }
    }

}