package com.example.roombasic

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URI

class MyAdapter(private val isUseCardView: Boolean = false) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var allWords: List<Word> = ArrayList()


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNumber: TextView = view.findViewById(R.id.textViewNumber)
        val textViewEnglish: TextView = view.findViewById(R.id.textViewEnglish)
        val textViewChinese: TextView = view.findViewById(R.id.textViewChinese)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        val itemView = layoutInflater.inflate(R.layout.cell_nomal, parent, false)
        val itemView: View =
            if (isUseCardView) layoutInflater.inflate(
                R.layout.cell_card,
                parent,
                false
            ) else layoutInflater.inflate(R.layout.cell_nomal, parent, false)
//        val itemView = layoutInflater.inflate(R.layout.cell_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word: Word = allWords[position]
        holder.textViewNumber.text = (position + 1).toString()
        holder.textViewEnglish.text = word.englishWord.toString()
        holder.textViewChinese.text = word.chineseMeaning.toString()
        holder.itemView.setOnClickListener {
            val uri: Uri? = Uri.parse("https://m.youdao.com/dict?le=eng&q=${holder.textViewEnglish.text}")
           val intent:Intent =  Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = allWords.size
}