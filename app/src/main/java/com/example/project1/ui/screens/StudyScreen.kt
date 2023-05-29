package com.example.project1.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import com.example.project1.ui.components.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
@Composable
fun StudyScreen(statusBarHeight: Int) {
    Column(modifier = Modifier) {
        TopAppBar(statusBarHeight) {
            Text("學習頁")
        }
        Text("LearningPage")
    }
}

@Preview
@Composable
fun StudyScreenPreview() {
    StudyScreen(30)
}

