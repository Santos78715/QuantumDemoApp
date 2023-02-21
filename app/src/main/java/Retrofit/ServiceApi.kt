package Retrofit

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val base_url = "https://newsapi.org/v2/"
const val  api = "3b6f3c2f04c9423597aa3a1c1d30931d"
interface ServiceApi {

    @GET(value = "top-headlines?country=us&category=business&apiKey=$api")
    fun getHeadlines(): Call<ModelClass>
}

object NewsService{
    val newsinstance : ServiceApi
    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
        newsinstance = retrofit.create(ServiceApi::class.java)
    }
}
