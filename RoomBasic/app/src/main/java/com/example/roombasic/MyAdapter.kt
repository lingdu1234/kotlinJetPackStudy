package com.example.roombasic

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.thread

class MyAdapter internal constructor(private val useCardView: Boolean, private val wordViewModel: WordViewModel) : Adapter<MyAdapter.MyViewHolder>() {
    var allWords: List<Word> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View
        itemView = if (useCardView) {
            layoutInflater.inflate(R.layout.cell_card_2, parent, false)
        } else {
            layoutInflater.inflate(R.layout.cell_normal_2, parent, false)
        }
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = allWords[position]
        holder.textViewNumber.text = (position + 1).toString()
        holder.textViewEnglish.text = word.word
        holder.textViewChinese.text = word.chineseMeaning
        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(null)
        if (word.isChineseInvisible) {
            holder.textViewChinese.visibility = View.GONE
            holder.aSwitchChineseInvisible.isChecked = true
        } else {
            holder.textViewChinese.visibility = View.VISIBLE
            holder.aSwitchChineseInvisible.isChecked = false
        }
        holder.itemView.setOnClickListener {
            val uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textViewEnglish.text)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }
        holder.aSwitchChineseInvisible.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.textViewChinese.visibility = View.GONE
                word.isChineseInvisible = true
                /////数据库操作 一定要放到子线程中，不然要出问题，而且不要包裹太多 只包裹数据库操作的语句,使用 thread 或者 GlobalScope.launch 包裹
                GlobalScope.launch {
                    wordViewModel.wordDao!!.updateWords(word)
                }
            } else {
                holder.textViewChinese.visibility = View.VISIBLE
                word.isChineseInvisible = false
                thread {
                    wordViewModel.wordDao?.updateWords(word)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return allWords.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var textViewNumber: TextView = itemView.findViewById(R.id.textViewNumber)
        var textViewEnglish: TextView = itemView.findViewById(R.id.textViewEnglish)
        var textViewChinese: TextView = itemView.findViewById(R.id.textViewChinese)
        var aSwitchChineseInvisible: Switch = itemView.findViewById(R.id.switchChineseInvisible)

    }
}