package com.example.bankmandiri.Ui.Main

import android.app.DatePickerDialog.OnDateSetListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

            itemView.setOnClickListener {
                listener(news )
            }

        }

    }
}