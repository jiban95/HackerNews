package com.example.demoapp.presentation.storyList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.data.model.ArticlesDetailsRes

class TopStoryListAdapter(
    private val listener: MainActivity,
    private val dataList: MutableList<ArticlesDetailsRes>
) :
    RecyclerView.Adapter<TopStoryListAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: ConstraintLayout = itemView.findViewById(R.id.cardView)
        val textTitle: AppCompatTextView = itemView.findViewById(R.id.textTitle)
        val textScore: AppCompatTextView = itemView.findViewById(R.id.textScore)
        val textDescendant: AppCompatTextView = itemView.findViewById(R.id.textDescendant)
        val textUser: AppCompatTextView = itemView.findViewById(R.id.textUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = dataList[position].title
        holder.textScore.text = dataList[position].score.toString()
        holder.textDescendant.text = dataList[position].descendants.toString()
        holder.textUser.text = dataList[position].by
        holder.cardView.setOnClickListener { listener.onItemClick(dataList[position].url) }
    }

    interface AdapterItemClick {
        fun onItemClick(url: String)
    }
}