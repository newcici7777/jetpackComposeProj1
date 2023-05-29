package com.example.project1

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.project1.ui.screens.MainFrame
import com.example.project1.ui.theme.Project1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //獲取狀態欄高度
        var statusBarHeight = 0
        val resourceId = resources.getIdentifier(
            "status_bar_height","dimen","android")
        if(resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }

        //systemUiVisibility was deprecated in API level 30.
        //SystemUiVisibility flags are deprecated. Use WindowInsetsController instead.

        //可用[隱藏狀態欄]/[啟用全屏模式]的關鍵字去搜尋
        //沉浸模式, 全營幕狀態列往下滑 狀態列會顯示
        //HOME鍵往上滑，HOME鍵會出現
        //參考以下網址
        //https://developer.android.com/training/system-ui/immersive?hl=zh-cn
//        window.statusBarColor = Color.Transparent.value.toInt() // 設置透明度
//        //隱藏狀態檻
//        window.decorView.systemUiVisibility = (
//                 View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                )

        //使用這個會讓buttonNavigation被遮住
       WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            Project1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainFrame(statusBarHeight)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project1Theme {
        //Greeting("Android")
        MainFrame(30)
    }
}