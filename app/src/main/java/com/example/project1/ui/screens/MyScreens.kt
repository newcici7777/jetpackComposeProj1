package com.example.project1.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import com.example.project1.ui.components.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyScreens() {
    Column(modifier = Modifier) {
        TopAppBar() {
            Text("MyPage")
        }
        Text("MyPage")
    }
}

@Preview
@Composable
fun MyScreensPreview() {
    MyScreens()
}

