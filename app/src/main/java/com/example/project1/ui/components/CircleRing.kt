package com.example.project1.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1.viewmodel.TaskViewModel

@Composable
fun CircleRing(boxWidthDp: Int,vm:TaskViewModel) {
    val strokeWith = 30f
    Canvas(modifier = Modifier.size(boxWidthDp.dp)) {
        //方式一
        //翻轉180度
        rotate(180f) {
            drawArc(
                Color(0, 0, 0, 33),//黑色rgb都0，%3透明度設33
                startAngle = -10f,//起點從-10開始
                sweepAngle = 200f,//畫200度
                useCenter = false, //圓點之間連起來
                style = Stroke(strokeWith, cap = StrokeCap.Round) // 邊框
            )
        }
        //方式二
//        drawArc(
//            Color(0, 0, 0, 33),//黑色rgb都0，%3透明度設33
//            startAngle = 160f,
//            sweepAngle = 220f,
//            useCenter = false, //圓點之間連起來
//            style = Stroke(30f, cap = StrokeCap.Round) // 邊框
//        )
        //覆寫同樣的位置就可以部分蓋住
        rotate(180f) {
            drawArc(
                Color.White,
                startAngle = -10f,
                sweepAngle = vm.pointOfYearPercent,//填滿是200 一半是100 2/3是133
                useCenter = false, //圓點之間連起來
                style = Stroke(strokeWith, cap = StrokeCap.Round) // 邊框
            )
        }
    }
}

