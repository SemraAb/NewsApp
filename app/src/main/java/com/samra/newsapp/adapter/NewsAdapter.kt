package com.samra.newsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samra.newsapp.data.network.model.Article
import com.samra.newsapp.databinding.RecyclerRowNewsBinding

class NewsAdapter (var list : List<Article>, var listener : OnCLickListener): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    inner class NewsHolder(var binding: RecyclerRowNewsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = RecyclerRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        Glide.with(holder.binding.root)
            .load(list[position].urlToImage)
            .into(holder.binding.newsImage)
        holder.binding.titleText.setText(list[position].title)
        holder.binding.dateText.setText(list[position].publishedAt?.split('T')?.get(0) ?: "")
        holder.binding.authorText.setText(list[position].author)
        holder.binding.root.setOnClickListener {
            listener.onClick(list[position])
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }



    @SuppressLint("NotifyDataSetChanged")
    fun onDataChange(data : List<Article>){
        list = data
        notifyDataSetChanged()
    }
}

interface OnCLickListener{
    fun onClick(item : Article)
}