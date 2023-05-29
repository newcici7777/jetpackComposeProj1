package com.example.project1.model.entity

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 導航物件
 * @property title 底部導航標題
 * @property icon 底部導航圖標
 */
data class NavigationItem(
    val title:String, //底部導航標題
    val icon:ImageVector //底部導航圖標
    )
