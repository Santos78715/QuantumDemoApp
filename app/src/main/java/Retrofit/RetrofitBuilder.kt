package Retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
    private val base_url = "https://newsapi.org/v2/"


    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()


        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(base_url).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
        }
    }
}