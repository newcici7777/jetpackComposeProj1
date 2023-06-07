package com.example.project1.ui.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationContent(vm: MainViewModel) {
    //無限徝環滾動
    //虛擬數量
    val virtualCount = Int.MAX_VALUE
    //實際數量
    val actualCount = vm.notifications.size
    //初始圖片下標
    val initialIndex = virtualCount / 2
    val pagerState = rememberPagerState(
        initialPage = initialIndex,
        initialPageOffsetFraction = 0f
    ) {
        virtualCount
    }
    val coroutineScope = rememberCoroutineScope()
    //自動輪播
    //監聽什麼時候創建 什麼時候銷毀
    DisposableEffect(Unit) {
        val timer = Timer() //創建定時器
        timer.schedule(object : TimerTask() {
            override fun run() {
                //參考rememberPagerState 裡的PagerState, 可以拿到pageCount 總數 / currentPage 當前的頁面
                //animateScrollToPage是掛起函數，要放在coroutine Scope
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1) // 滾動到當前頁(currentPage)的下一頁(+1)
                }
                //pagerState.animateScrollBy() //滾動到某一數值
            }
        }, 2000, 3000) //每三秒循環一次
        onDispose { //銷毀定時器
            timer.cancel()
        }
    }
    Row(
        modifier = Modifier
            .padding(8.dp) //上下左右padding
            .clip(RoundedCornerShape(8.dp)) //圓角
            //上面二項要放在background前，不然會沒效果
            .background(Color(0x22149EE7)) //背景顏色
            .height(45.dp) //高度
            .padding(horizontal = 8.dp)//後面有水平padding
        , verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "最新活動", color = Color(0xFF149EE7),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        //垂直居中 .align(Alignment.CenterVertically)無效
        //用 Modifier.padding(vertical = 10.dp) 取代
        VerticalPager(
            state = pagerState,
            modifier = Modifier.padding(vertical = 10.dp).weight(1f),
            horizontalAlignment = Alignment.Start, //靠左
        ) { index ->
            //floorDiv 向下取整
            //val actualIndex = index - (index.floorDiv(actualCount)) * actualCount
            //改寫如下
            //(index - initialIndex)要讓圖片從第0張開始
            val actualIndex = (index - initialIndex).floorMod(actualCount)
            Text(
                vm.notifications[actualIndex],
                color = Color(0xFF333333),
                fontSize = 14.sp,
                maxLines = 1, //最多只有一行
                overflow = TextOverflow.Ellipsis //點點
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "more",
            color = Color(0xFF666666),
            fontSize = 14.sp
        )
    }
}

/**
 * 擴展函數
 *
 * @param other
 * @return
 */
private fun Int.floorMod(other: Int): Int = when (other) {
    //index.floorMod(actualCount) 呼叫方式
    //this就是上一行註解中的index , 若index為0 直接返回0
    0 -> this
    //(index.floorDiv(actualCount)) * actualCount
    else -> this - floorDiv(other) * other
}


