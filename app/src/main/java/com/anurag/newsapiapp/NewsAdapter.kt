package com.anurag.newsapiapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val articles: List<Articles>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var articles = articles[position]
        holder.newsTitle.text = articles.title
        holder.newsDescription.text = articles.description
        Glide.with(context).load(articles.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            Toast.makeText(context,articles.title,Toast.LENGTH_SHORT).show()
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("url", articles.url)
            context.startActivities(arrayOf(intent))
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.news_img)
        var newsTitle = itemView.findViewById<TextView>(R.id.news_Title)
        var newsDescription = itemView.findViewById<TextView>(R.id.news_descreption)

    }

}