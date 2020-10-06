package com.example.roombasic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word (@field:ColumnInfo(name = "english_word") var word: String, @field:ColumnInfo(name = "chinese_meaning") var chineseMeaning: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "chinese_invisible")
    var isChineseInvisible = false

}