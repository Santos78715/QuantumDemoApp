package com.example.quantumdemo.signin

import Retrofit.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quantumdemo.R
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        getNews()
        signout()
    }

    private fun getNews() {
        val news: Call<ModelClass> = NewsService.newsinstance.getHeadlines()
        news.enqueue(object : Callback<ModelClass> {
            override fun onFailure(call: Call<ModelClass>, t: Throwable) {
                Toast.makeText(applicationContext, "Hey", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ModelClass>, response: Response<ModelClass>) {
                val news: ModelClass? = response.body()
                if (news != null) {
                    adapter = NewsAdapter(this@NewsActivity, news.articles)
                    var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@NewsActivity)

                }
            }
        })
    }

    private fun signout(){
        var signout = findViewById<Button>(R.id.signout)
        signout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}