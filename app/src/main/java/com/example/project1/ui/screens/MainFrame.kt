package com.example.project1.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.project1.model.entity.NavigationItem
import androidx.compose.ui.Modifier
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFrame(statusBarHeight: Int) {
    val navigationItems = listOf(
        NavigationItem(title = "learn", icon = Icons.Filled.Home),
        NavigationItem(title = "task", icon = Icons.Filled.DateRange),
        NavigationItem(title = "my", icon = Icons.Filled.Favorite)
    )
    //預設是選0
    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(bottomBar = {
        //使用MaterialTheme的color
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.navigationBarsPadding()) {
            //遍歷navigationItem的數組，拿出title跟icon
            navigationItems.forEachIndexed { index, navigationItem ->
                BottomNavigationItem(
                    selected = currentNavigationIndex == index,
                    onClick = {
                        currentNavigationIndex = index
                    },
                    icon = {
                        Icon(imageVector = navigationItem.icon, contentDescription = null)
                    },
                    label = {
                        Text(text = navigationItem.title)
                    },
                    alwaysShowLabel = false, //false點擊才show label,總是show label是true
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF999999)
                )
            }
        }
    }) {
        when(currentNavigationIndex) {
            0 -> StudyScreen()
            1 -> TaskScreen()
            2 -> MyScreens()
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame(30)
}

