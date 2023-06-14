package com.example.project1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var taskDate by mutableStateOf("xxxxxxx")
        private set

    //學年總積分
    var totalPointOfYear = 13500

    //學年積分
    var pointOfYear by mutableStateOf(6750)
        private set

    //積分進度 = 200f * pointOfYear / 學年總積分
    var pointOfYearPercent by mutableStateOf(0f) //預設0
        private set

    fun updatePointPercent() {
        //若pointOfYear設成13500
        // (200 * 13500) / 13500 = 200
        //200為全部填滿
        //若pointOfYear設成6750  為13500的一半(13500/2)
        //(200 * 6750) / 6750 = 100為半圓
        pointOfYearPercent = (200f * pointOfYear) / totalPointOfYear
    }

    var pointsOfWeek by mutableStateOf(listOf(0f, 2.5f, 5f, 6f, 7f, 15f, 2f))
        private set
    val weeks = listOf("02/05","02/06","02/07","02/08","02/09","02/10","今天")
}