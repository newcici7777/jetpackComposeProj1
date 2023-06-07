package com.example.project1.viewmodel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.project1.model.entity.Category
import com.example.project1.model.entity.DataType
import com.example.project1.model.entity.SwiperEntity

//繼承系統的view model
class MainViewModel : ViewModel() {
    //分類數據
    val categories by mutableStateOf(
        listOf(
            Category("category1"),
            Category("category2"),
            Category("category3"),
            Category("category4")
        )
    )
    //當前分類下標
    var categoryIndex by mutableStateOf(0)
        private set

    //不能取名成setCategoryIndex，會跟上面private set方法會重覆，編譯器會衝突
    /**
     * 更新分類index
     *
     * @param index
     */
    fun updateCategoryIndex(index:Int){
        categoryIndex = index
    }

    val types by mutableStateOf(listOf(
        DataType("info1", Icons.Default.Description),
        DataType("info2", Icons.Default.SmartDisplay)
    ))
    //當前type選中
    var currentTypeIndex by mutableStateOf(0)
        private set

    var showArticleList by mutableStateOf(true)
        private set
    /**
     * 更新類型下標
     *
     * @param index
     */
    fun updateTypeIndex(index:Int) {
        currentTypeIndex = index
        showArticleList = currentTypeIndex == 0
    }

    //輪播圖
    val swiperData = listOf(
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner1.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner2.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner3.webp")
    )

    val notifications = listOf("test1","test2")
}