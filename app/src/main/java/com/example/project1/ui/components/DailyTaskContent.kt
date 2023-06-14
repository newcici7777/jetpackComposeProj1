package com.example.project1.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyTaskContent() {
    DailyTaskItem(
        title = "文章學習",
        secondaryText = "xxxxxxxxxxxxxxxxxxxxx",
        desc = "descxxxxxx",
        1f
    )
    DailyTaskItem(
        title = "文章學習",
        secondaryText = "xxxxxxxxxxxxxxxxxxxxx",
        desc = "descxxxxxx",
        0.7f
    )
}

@Composable
fun DailyTaskItem(title: String, secondaryText: String, desc: String, percent: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(7f)
        ) {
            Text(title, fontSize = 16.sp, color = Color(0xFF333333))
            Row {
                Text(secondaryText, fontSize = 14.sp, color = Color(0xFF333333))
                Icon(imageVector = Icons.Default.HelpOutline, contentDescription = null)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(progress = percent,
                    modifier = Modifier.weight(5f))
                Text(
                    desc,
                    fontSize = 10.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier
                        .weight(5f, fill = false) //fill預設是填滿
                        .padding(horizontal = 8.dp)
                )
            }
        }
        OutlinedButton(
            onClick = {},
            border =
            if(percent >= 1f) ButtonDefaults.outlinedButtonBorder
            else BorderStroke(1.dp, Color(0xFFFF5900)),//外框顏色
            shape = CircleShape,//圓角
            modifier = Modifier.weight(3f),
            //把按鈕換顏色，注意！ButtonDefaults是material3
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.DarkGray),
            enabled = percent < 1f //是否啟用
        ) {
            Text("Go")
        }
    }
}

@Preview
@Composable
fun DailyTaskPriev() {
    DailyTaskContent()
}