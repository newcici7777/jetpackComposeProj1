package com.example.project1.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1.ui.components.ChartView
import com.example.project1.ui.components.appBarHeight
import com.example.project1.viewmodel.TaskViewModel
import com.example.project1.ui.components.CircleRing
import com.example.project1.ui.components.DailyTaskContent

@Composable
fun TaskScreen(taskVM: TaskViewModel = viewModel()) {
    var boxWidthDp: Int

    //營幕寬度/2 取得相同的寬高的正方形大小，才能置中的正方形
    with(LocalConfiguration.current) {
        boxWidthDp = screenWidthDp / 2
    }

    //taskVM.pointOfYear改變時，就會重新計算百分比
    LaunchedEffect(taskVM.pointOfYear) {
        taskVM.updatePointPercent()
    }
    Column(
        modifier = Modifier
            .fillMaxSize() //鋪滿全屏
            .background(
                //背景漸變色從上到下垂直漸變 從藍變白
                Brush.verticalGradient(
                    listOf(Color(0xFF149EE7), Color.White)
                )
            )
    ) {
        //
        Row(
            modifier = Modifier
                .statusBarsPadding() //系統列
                .height(appBarHeight),//appbar的高度
            verticalAlignment = Alignment.CenterVertically //垂直置中
        ) {
            Text(
                "TaskContent",
                modifier = Modifier.fillMaxWidth(),//一定要設定不然不能居中
                textAlign = TextAlign.Center,//置中
                color = Color.White,
                fontSize = 18.sp
            )
        }
        //lazyColumn橫向置中，很重要！
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Text(
                    taskVM.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            item {
                Box(
                    contentAlignment = Alignment.Center, //垂直置中
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ) {
                    CircleRing(boxWidthDp, taskVM)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            buildAnnotatedString {
                                //append(taskVM.pointOfYear.toString())
                                append(taskVM.pointOfYearPercent.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("point")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White
                        )
                    }
                }
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp) //往上移動
                ) {
                    Column() {
                        Text(
                            text = "${taskVM.totalPointOfYear}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "規定分數",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                    Column() {
                        Text(
                            text = "${taskVM.totalPointOfYear - taskVM.pointOfYear}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "規定分數",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))//頂端二邊有圓角
                        .fillMaxSize() //填滿
                        .background(Color.White)//白色
                        .padding(top = 8.dp) //與上面的元間有8dp的距離
                        .padding(horizontal = 8.dp, vertical = 8.dp) //與左邊及下面有8dp距離
                ) {
                    Text(text = "明細", fontSize = 16.sp, color = Color(0xFF333333))
                    //折線圖
                    ChartView(
                        taskVM.pointsOfWeek,
                        modifier = Modifier.padding(vertical = 12.dp) //將表格往下移12dp
                    )
                    //圖表日期顯示
                    Row() {
                        taskVM.weeks.forEach {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = Color(0xFF999999),
                                textAlign = TextAlign.Center, //置中
                                modifier = Modifier.weight(1f) //設權重分散對齊
                            )
                        }
                    }

                    //
                    DailyTaskContent()

                }
            }
        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}

