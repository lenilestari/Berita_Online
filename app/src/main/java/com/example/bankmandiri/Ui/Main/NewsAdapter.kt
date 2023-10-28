package com.example.bankmandiri.Ui.Main

import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bankmandiri.Data.Response.ArticlesItem
import com.example.bankmandiri.Data.Response.NewResponse
import com.example.bankmandiri.R

class NewsAdapter (private val listener: (ArticlesItem)-> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = listOf<ArticlesItem>()

    fun setNews(news : List<ArticlesItem>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: ArticlesItem) {
            itemView.findViewById<TextView>(R.id.tvTitle).text = news.title
            itemView.findViewById<TextView>(R.id.tvPublishedAt).text = "Published on: ${news.publishedAt}"

            val authorTextView = itemView.findViewById<TextView>(R.id.Author)
            authorTextView.text = "Author: ${news.author}"

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.image))
                .into(itemView.findViewById<ImageView>(R.id.ivNews))

            itemView.findViewById<Button>(R.id.btnReadMore).setOnClickListener {
                val newsUrl = news.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
                itemView.context.startActivity(intent)
            }
        }
    }


}