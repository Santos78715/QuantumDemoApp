package com.example.quantumdemo.signin

import Retrofit.Article
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quantumdemo.R

class NewsAdapter(val context: Context, val article : List<Article> ): RecyclerView.Adapter<NewsAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view : View  = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return article.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val articles = article[position]
        holder.description.text = articles.description
        holder.title.text = articles.title
        holder.published.text = articles.publishedAt
        Glide.with(context).load(articles.urlToImage).into(holder.newsimage)

    }


    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsimage= itemView.findViewById<ImageView>(R.id.imageview)
        var title = itemView.findViewById<TextView>(R.id.title)
        var description = itemView.findViewById<TextView>(R.id.description)
        val published = itemView.findViewById<TextView>(R.id.published)
    }
}