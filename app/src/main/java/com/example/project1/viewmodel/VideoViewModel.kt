package com.example.project1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project1.model.entity.VideoEntity

class VideoViewModel : ViewModel() {
    var list = listOf(
        VideoEntity(
            title = "video1",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video2",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video3",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video4",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video5",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video6",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video7",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video8",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        ),
        VideoEntity(
            title = "video9",
            type = "video class",
            duration = "00:02:00",
            imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp"
        )
    )
        private set

}