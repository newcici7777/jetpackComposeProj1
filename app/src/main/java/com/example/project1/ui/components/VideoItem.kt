package com.example.project1.ui.components


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import com.example.project1.model.entity.VideoEntity

@Composable
fun VideoItem(videoEntity: VideoEntity) {
    val constrainSet = ConstraintSet {
        val title = createRefFor("title")
        val cover = createRefFor("cover")
        val type = createRefFor("type")
        val duration = createRefFor("duration")
        val divider = createRefFor("divider")
        constrain(cover) {
            start.linkTo(parent.start) //右邊對齊 父
            centerVerticallyTo(parent) //垂直居中 父
            width = Dimension.value(115.dp) // 寬度 搭配比例aspectRatio才會真正縮小
        }
        constrain(title) {
            start.linkTo(cover.end, margin = 8.dp)//左邊對到圖片的右邊
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints // 寬度一定要自適應<十分重要>
        }
        constrain(type) {
            start.linkTo(title.start)//左邊對到title的左邊
            bottom.linkTo(parent.bottom) //下面與父 的下面對齊
        }
        constrain(duration) {
            end.linkTo(parent.end)//右邊對到父佈局的右邊
            bottom.linkTo(parent.bottom) //下面與父 的下面對齊
        }
        constrain(divider) {
            bottom.linkTo(parent.bottom, margin = (-8).dp) //向下移
        }
    }

    ConstraintLayout(
        constrainSet,
        modifier = Modifier
            .fillMaxWidth() //設置最大寬度
            .padding(8.dp)
    ) {
        AsyncImage(
            model = videoEntity.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .layoutId("cover")
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp)) //圓角一定要搭配裁剪，不然沒作用
        ,
            contentScale = ContentScale.Crop //裁剪
        )
        Text(
            text = videoEntity.title,
            fontSize = 16.sp,
            color = Color(0xFF666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("title")
        )
        Text(
            text = videoEntity.type,
            fontSize = 16.sp,
            color = Color(0xFF666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("type")
        )
        Text(
            text = "時長${videoEntity.duration}",
            fontSize = 16.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("duration")
        )
        Divider(
            modifier = Modifier
                .layoutId("divider")
        )
    }
}


