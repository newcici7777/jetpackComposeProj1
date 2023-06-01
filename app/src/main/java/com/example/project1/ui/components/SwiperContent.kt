package com.example.project1.ui.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.project1.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperContent(vm: MainViewModel = viewModel()) {
    //無限徝環滾動
    //虛擬頁數
    val virtualCount = Int.MAX_VALUE
    //實際頁數
    val actualCount = vm.swiperData.size
    //初始圖片下標
    val initialIndex = virtualCount / 2
    val pagerState = rememberPagerState(initialIndex)
    val coroutineScope = rememberCoroutineScope()
    //自動輪播
    //監聽什麼時候創建 什麼時候銷毀
    DisposableEffect(Unit) {
        val timer = Timer() //創建定時器
        timer.schedule(object : TimerTask(){
            override fun run() {
                //參考rememberPagerState 裡的PagerState, 可以拿到pageCount 總數 / currentPage 當前的頁面
                //animateScrollToPage是掛起函數，要放在coroutine Scope
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1) // 滾動到當前頁(currentPage)的下一頁(+1)
                }
                //pagerState.animateScrollBy() //滾動到某一數值
            }
        },3000,3000) //每三秒循環一次
        onDispose { //銷毀定時器
            timer.cancel()
        }
    }
    
    //輪播圖
    HorizontalPager(
        pageCount = virtualCount,
        state = pagerState,
        pageSpacing = 16.dp, //圖跟圖之間有空白
        modifier = Modifier
            .padding(horizontal = 8.dp) //圖片不會貼滿左右二邊
            .clip(RoundedCornerShape(8.dp)) //圓角
    ) { index ->
        //floorDiv 向下取整
        //val actualIndex = index - (index.floorDiv(actualCount)) * actualCount
        //改寫如下
        //(index - initialIndex)要讓圖片從第0張開始
        val actualIndex = (index - initialIndex).floorMod(actualCount)

        //pageContent: @Composable (page: Int) -> Unit
        //index 為 page: Int 會有page的參數 看上面的描述在HorizontalPager中
        //透過 index拿到圖片
        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth() //填滿
                .aspectRatio(7 / 3f) //比例7比3
            ,
            contentScale = ContentScale.Crop //裁剪
        )
    }
}

/**
 * 擴展函數
 *
 * @param index
 * @return
 */
private fun Int.floorMod(other:Int) : Int = when(other) {
    //index.floorMod(actualCount) 呼叫方式
    //this就是上一行註解中的index , 若index為0 直接返回0
    0 -> this
    //(index.floorDiv(actualCount)) * actualCount
    else -> this - floorDiv(other) * other
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperContent1(vm: MainViewModel = viewModel()) {
    //輪播圖
    HorizontalPager(
        pageCount = vm.swiperData.size,
        pageSpacing = 16.dp, //圖跟圖之間有空白
        modifier = Modifier
            .padding(horizontal = 8.dp) //圖片不會貼滿左右二邊
            .clip(RoundedCornerShape(8.dp)) //圓角
    ) { index ->
        //pageContent: @Composable (page: Int) -> Unit
        //index 為 page: Int 會有page的參數 看上面的描述在HorizontalPager中
        //透過 index拿到圖片
        AsyncImage(
            model = vm.swiperData[index].imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth() //填滿
                .aspectRatio(7 / 3f) //比例7比3
            ,
            contentScale = ContentScale.Crop //裁剪
        )
    }
}

@Preview
@Composable
fun SwiperContentPreview() {
    SwiperContent()
}

