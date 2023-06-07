package com.example.project1.ui.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
    //虛擬數量
    val virtualCount = Int.MAX_VALUE
    //實際數量
    val actualCount = vm.swiperData.size
    //初始圖片下標
    val initialIndex = virtualCount / 2
    //val pagerState = rememberPagerState(initialIndex)
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
        state = pagerState,
        pageSpacing = 16.dp, //圖跟圖之間有空白
        modifier = Modifier
            .padding(horizontal = 8.dp) //圖片不會貼滿左右二邊
            .clip(RoundedCornerShape(8.dp)) //圓角
    ) { index ->
        //假設實際只有3筆
        //但virtualCount虛擬數量設為9
        //page頁數    0         1        2
        //虛擬index 0,1,2     3,4,5    6,7,8
        //實際下標  [0,1,2]   [0,1,2]  [0,1,2]
        //分為4頁，每一頁的下標分別為真實的數量0,1,2
        //虛擬index-[第幾頁(虛擬index/實際總筆數)*實際總筆數]= 實際下標
        //如何取得真正下標的公式假設虛擬index為3-[第幾頁(虛擬index3/實際總筆數3)*實際總筆數3] = 實際下標0
        //如何取得真正下標的公式假設虛擬index為4-[第幾頁(虛擬index4/實際總筆數3)*實際總筆數3] = 實際下標1
        //如何取得真正下標的公式假設虛擬index為5-[第幾頁(虛擬index5/實際總筆數3)*實際總筆數3] = 實際下標2

        //但現在有一個問題，初始值index，假設虛擬總筆數為8 / 2 = 4
        //4為虛擬初始值，套上面的公式 4-[(4/3)*3]=下標1
        //但希望下標是從0開始
        //原本
        //page頁數    0         1        2
        //虛擬index 0,1,2     3,4,5    6,7,8
        //實際下標  [0,1,2]   [0,1,2]  [0,1,2]
        //下標往後推移,初始值由4開始
        //page頁數   0        1
        //虛擬index 4,5,6,    7
        //實際下標  [0,1,2]   [0]

        //如何取得真正下標的公式假設(虛擬index為4-4為虛擬初始值)-[第幾頁((虛擬index為4-4為虛擬初始值)/實際總筆數3)*實際總筆數3] = 實際下標0
        //如何取得真正下標的公式假設(虛擬index為5-4為虛擬初始值)-[第幾頁((虛擬index為5-4為虛擬初始值)/實際總筆數3)*實際總筆數3] = 實際下標1
        //如何取得真正下標的公式假設(虛擬index為6-4為虛擬初始值)-[第幾頁((虛擬index為6-4為虛擬初始值)/實際總筆數3)*實際總筆數3] = 實際下標2
        //如何取得真正下標的公式假設(虛擬index為7-4為虛擬初始值)-[第幾頁((虛擬index為7-4為虛擬初始值)/實際總筆數3)*實際總筆數3] = 實際下標0
        //floorDiv 向下取整
        //val actualIndex = index - (index.floorDiv(actualCount)) * actualCount
        //改寫如下
        //(index - initialIndex)要讓圖片從第0張開始
//        Log.d("====","index  :${(index )}")
//        Log.d("====","initialIndex  :${(initialIndex )}")
//        Log.d("====","index - initialIndex :${(index - initialIndex)}")
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
 * @param other 實際總筆數
 * @return
 */
private fun Int.floorMod(other:Int) : Int = when(other) {
    //index.floorMod(actualCount) 呼叫方式
    //this就是上一行註解中的index , 若總數為0 直接返回0
    0 -> this
    //(index.floorDiv(actualCount)) * actualCount
    else -> {
//        Log.d("====", "this:${this}")
//        Log.d("====", "other:${other}")
//        Log.d("====", "floor:${floorDiv(other)}")
        this - floorDiv(other) * other
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwiperContent1(vm: MainViewModel = viewModel()) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        vm.swiperData.size
    }
    //輪播圖
    HorizontalPager(
        state = pagerState,
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


