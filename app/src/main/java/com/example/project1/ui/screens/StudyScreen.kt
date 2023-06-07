package com.example.project1.ui.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import com.example.project1.ui.components.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1.model.entity.VideoEntity
import com.example.project1.ui.components.ArticleItem
import com.example.project1.ui.components.NotificationContent
import com.example.project1.ui.components.SwiperContent
import com.example.project1.ui.components.VideoItem
import com.example.project1.viewmodel.ArticleViewModel
import com.example.project1.viewmodel.MainViewModel
import com.example.project1.viewmodel.VideoViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StudyScreen(
    vm: MainViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    videoViewModel: VideoViewModel = viewModel()
) {//自動創建viewModel 不用自已建立
    Column(modifier = Modifier) {
        //標題
        TopAppBar(modifier = Modifier.padding(8.dp)) {//上下左右都有padding

            //搜索
            Surface(
                modifier = Modifier
                    //圓角矩形
                    .clip(RoundedCornerShape(16.dp))
                    //權重 1f代表盡可能為最大
                    .weight(1f),
                //背景顏色白色透明度30
                color = Color(0x33FFFFFF)
            ) {
                //位置往右移8dp 往下移4dp
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp) //圖片大小
                    )
                    Text(
                        text = "Search course",
                        color = Color.White,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis //文字太多 點點點顯示
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp)) //寬度為8dp的空格
            Text(text = "學習\n進度", fontSize = 10.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp)) //寬度為8dp的空格
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp)) //寬度為8dp的空格
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }
        //分類標籤
        //背景顏色透明度%6 0x66
        TabRow(
            selectedTabIndex = vm.categoryIndex,
            backgroundColor = Color(0x66149EE7),
            contentColor = Color(0x66149EE7) //透過contentColor就可以把indicator顏色改變
        ) {
            vm.categories.forEachIndexed { index, category ->
                Tab(
                    selected = vm.categoryIndex == index, onClick = {
                        vm.updateCategoryIndex(index)
                    },
                    selectedContentColor = Color(0xFF149EE7),//選中顏色
                    unselectedContentColor = Color(0xFF666666) //未選中顏色
                ) {
                    //向下新增8dp
                    Text(
                        text = category.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 14.sp
                    )
                }
            }
        }
        TabRow(
            selectedTabIndex = vm.currentTypeIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color(0x66149EE7), //透過contentColor就可以把indicator顏色改變
            indicator = {},
            divider = {} //分隔線沒有
        ) {
            vm.types.forEachIndexed { index, dataType ->
                LeadingIconTab(
                    selected = vm.currentTypeIndex == index, onClick = {
                        vm.updateTypeIndex(index)
                    },
                    selectedContentColor = Color(0xFF149EE7),//選中顏色
                    unselectedContentColor = Color(0xFF666666), //未選中顏色
                    icon = {
                        Icon(imageVector = dataType.icon, contentDescription = null)
                    },
                    text = {
                        Text(
                            text = dataType.title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }

        val lazyListState = rememberLazyListState()
        LazyColumn(state = lazyListState) {
            //SwiperContent與NotificationContent用item包起來
            item {
                SwiperContent(vm)
            }
            item {
                NotificationContent(vm)
            }
            //if(vm.currentTypeIndex == 0) {
            if (vm.showArticleList) {
                //列表
                items(articleViewModel.list) { article ->
                    ArticleItem(article)
                }
            } else {
                //視頻列表
                items(videoViewModel.list) { videoEntity ->
                    VideoItem(videoEntity)
                }
            }
        }

    }
}


