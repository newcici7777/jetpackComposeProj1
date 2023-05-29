package com.example.project1.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1.ui.theme.Blue200
import com.example.project1.ui.theme.Blue700
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @param statusBarHeight 狀態欄高度
 * @param content 標題欄內容
 */
@Composable
fun TopAppBar(statusBarHeight: Int, content: @Composable () -> Unit) {
    var systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = Unit){
        //第二個參數可以設置系統列上的文字顏色，先設置默認
        systemUiController.setStatusBarColor(Color.Transparent)
    }
    //標題欄高度
    val appBarHeight = 56.dp

    //轉換狀態欄高度為dp
    //取得屏幕密度 把statusBarHeight(Int)轉成dp
    val statusBarHeightDp = with(LocalDensity.current){
        //點擊toDp()，有一個擴展方法把int to dp
        // fun Int.toDp(): Dp = (this / density).dp
        statusBarHeight.toDp()
    }
    //Brush.horizontalGradient()橫向漸變
    //Brush.linearGradient()直向漸變
    //Brush.verticalGradient()水平漸變
    Row(
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    listOf(
                        Blue700,
                        Blue200,
                    )
                )
            )
            .fillMaxWidth() //設最寬
            .height(appBarHeight+ statusBarHeightDp) //設高度

            //Modifier.padding(all: Dp) 要一個類型dp的參數
            .padding(top = statusBarHeightDp)
        ,
        //標題垂直置中
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()

    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(30) {
        Text(text = "標題")
    }
}

