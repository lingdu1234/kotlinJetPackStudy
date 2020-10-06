package com.example.recordscore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreDataModel : ViewModel() {
    val aTeamScore: MutableLiveData<Int> get() = _aTeamScore
    val bTeamScore: MutableLiveData<Int> get() = _bTeamScore
    private var aBack: Int = 0
    private var bBack: Int = 0
    private val _aTeamScore = MutableLiveData<Int>()
    private val _bTeamScore = MutableLiveData<Int>()
    fun addScoreA(n: Int) {
        aBack = aTeamScore.value ?: 0
        _aTeamScore.value = aBack + n
    }

    fun addScoreB(n: Int) {
        bBack = bTeamScore.value ?: 0
        _bTeamScore.value = bBack + n
    }

    fun reset() {
        aTeamScore.value = 0
        bTeamScore.value = 0

    }

    fun undo() {
        aTeamScore.value = aBack
        bTeamScore.value = bBack
    }

}