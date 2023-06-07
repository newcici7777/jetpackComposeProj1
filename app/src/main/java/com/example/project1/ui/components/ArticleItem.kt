package com.example.project1.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.model.entity.ArticleEntity

@Composable
fun ArticleItem(article: ArticleEntity) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            article.title,
            color = Color(0xFF333333),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "來源:${article.source}",
                color = Color(0xFF999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            Text(text = article.timestamp)
        }
        Spacer(Modifier.height(8.dp))
        Divider()
    }
}



