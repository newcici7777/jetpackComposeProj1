package com.example.project1.ui.components


import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChartView(points: List<Float> , modifier: Modifier = Modifier) {
    //每一row的高度
    val heightForRow = 24

    //總row數
    val countForRow = 5

    //小圓圈半徑
    val circleRadius = 2.5

    //畫布寬度 = 營幕寬度 - 左邊padding - 右邊padding
    //畫布寬度 = 營幕寬度 - padding*2
    val canvasWidth = LocalConfiguration.current.screenWidthDp - (8 * 2)

    //總高度
    //每一row的高度*總row數+上下小圓的半徑(因為最底會突出來小半圓，最高會突出小半圓)
    val canvasHeight = heightForRow * countForRow + (circleRadius * 2)

    //一周有7天，要把畫布的寬度分成7等份，然後讓圓點座落在每一等份的中間位置
    val averageOfWidth = canvasWidth / 7
    val perY = 8 // (每一個row的高度24dp * 總row:5) /15(最高15積分) ，每一積分占8dp
    Canvas(
        modifier = modifier.size(
            width = canvasWidth.dp,
            height = canvasHeight.dp
        )
    ) {
        //畫直線
        for(index in 0 .. countForRow) {
            //canvas只接受px，要記得轉成px
            //最底部跟最頂部有小圓半徑的大小，所以y軸的位置都要往下偏移
            val y = (heightForRow * index + circleRadius).dp.toPx()
//每一個row的y值 從0的位置往下偏移2.5圓的半徑
//            y = 2.5
//            y = 26.5
//            y = 50.5
//            y = 74.5
//            y = 98.5
//            y = 122.5
            Log.d("====","y = ${(heightForRow * index + circleRadius)}")
            drawLine(Color(0xFFEEEEEE),
                start = Offset(0f, y), //x從0開始畫直線
                end = Offset(size.width, y), //x為畫布最大寬度 size.width為直接抓到畫布的大小
                strokeWidth = 2.5f,
                cap = StrokeCap.Round
            )
        }

        //畫圓圈折線
        for(index in 0 until points.count()) {
            //計算圓心位置
            val circleCenter = Offset(
                //一周有7天，要把畫布的寬度分成7等份 index為第幾等份*平均寬度
                //讓圓點座落在每一等份的中間位置
                (averageOfWidth * index + averageOfWidth / 2 ).dp.toPx(),
                //(每一row的高度 * 總row數) - (每一積分 * 8dp)
                ((heightForRow * countForRow) - (points[index] * perY) + circleRadius).dp.toPx()
            )
            Log.d("====","index = ${index} result = ${((heightForRow * countForRow) - (points[index] * perY) + circleRadius)}")
//尚未加上小圓半徑時的結果
//            index = 0 result = 120.0
//            index = 1 result = 100.0
//            index = 2 result = 80.0
//            index = 3 result = 72.0
//            index = 4 result = 64.0
//            index = 5 result = 40.0
//            index = 6 result = 104.0
// 加上小圓半徑時的結果
//            index = 0 result = 122.5
//            index = 1 result = 102.5
//            index = 2 result = 82.5
//            index = 3 result = 74.5
//            index = 4 result = 66.5
//            index = 5 result = 2.5
//            index = 6 result = 106.5
            drawCircle(Color(0xFF149EE7),
                radius = circleRadius.dp.toPx(),//半徑
                center = circleCenter, //圓心
                style = Stroke(width = 5f)
            )

            //畫圓心與下一個圓心之間的連線
            if(index < points.count() - 1) {//- 1因為最後一個點不用畫線
                //下一個圓心，所以只要把index + 1就可以抓到
                val nextCircleCenter = Offset(
                    //一周有7天，要把畫布的寬度分成7等份 index為第幾等份*平均寬度
                    //讓圓點座落在每一等份的中間位置
                    (averageOfWidth * (index + 1) + averageOfWidth / 2 ).dp.toPx(),
                    //(每一row的高度 * 總row數) - (每一積分 * 8dp)
                    ((heightForRow * countForRow) - (points[(index + 1)] * perY) + circleRadius).dp.toPx()
                )
                drawLine(Color(0xFF149EE7),
                    start = circleCenter,//當前的圓心
                    end = nextCircleCenter,//下一個圓心
                    strokeWidth = 5f)
            }
        }
    }
}
//
//@Preview
//@Composable
//fun ChartViewPreview() {
//    ChartView()
//}

