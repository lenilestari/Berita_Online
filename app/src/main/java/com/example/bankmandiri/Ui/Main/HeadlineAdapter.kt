package com.example.bankmandiri.Ui.Main

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
import com.example.bankmandiri.Data.Response.ArticlesItem2
import com.example.bankmandiri.R

class HeadlineAdapter(private val listener: (ArticlesItem2) -> Unit) :
    RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {

    private var news = listOf<ArticlesItem2>()

    fun setNews2(news: List<ArticlesItem2>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_headline, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: ArticlesItem2) {
            itemView.findViewById<TextView>(R.id.tvTitle2).text = news.title
            itemView.findViewById<TextView>(R.id.tvPublishedAt2).text = "Published on: ${news.publishedAt}"

            val authorTextView = itemView.findViewById<TextView>(R.id.Author2)
            authorTextView.text = "Author: ${news.author}"

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.image))
                .into(itemView.findViewById<ImageView>(R.id.ivNewsAtas2))

            itemView.findViewById<Button>(R.id.btnReadMore2).setOnClickListener {
                val newsUrl = news.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
                itemView.context.startActivity(intent)
            }
        }
    }
}
