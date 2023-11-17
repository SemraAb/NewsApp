    package com.samra.newsapp.adapter

    import android.annotation.SuppressLint
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import com.samra.newsapp.R
    import com.samra.newsapp.data.network.model.Article
    import com.samra.newsapp.databinding.RecyclerRowDailyNewsBinding
    import com.samra.newsapp.databinding.RecyclerRowNewsBinding

    class NewsCardAdapter(var list : List<Article> , var listener : OnCLickListener) : RecyclerView.Adapter<NewsCardAdapter.NewsCardHolder>() {
        class NewsCardHolder(var binding: RecyclerRowDailyNewsBinding): RecyclerView.ViewHolder(binding.root) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCardHolder {
            var binding = RecyclerRowDailyNewsBinding.inflate(LayoutInflater.from(parent.context), parent , false)
            return NewsCardHolder(binding)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: NewsCardHolder, position: Int) {
      //      Picasso.get().load(list[position].urlToImage).into(holder.binding.imageView)
            Glide.with(holder.binding.root)
                .load(list[position].urlToImage)
                .into(holder.binding.imageView)

            holder.binding.titleText.setText(list[position].title)
            holder.binding.root.setOnClickListener{
                listener.onClick(list[position])
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        fun onDataChange(data : List<Article>){
            list = data
            notifyDataSetChanged()
        }
    }