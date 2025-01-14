package com.dd2d.presentation.code_post.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.core.presentation.main_text.Main400Text
import com.dd2d.core.presentation.main_text.Main600Text
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model_dummy.DummyCodePostListItem

@Composable
internal fun CodePostListItemComponent(
    item: CodePostListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
) {
    ElevatedCard(
        onClick = onClick,
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 0.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
        ),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ){
            Main600Text(text = item.title, color = Color.Unspecified, fontSize = 21.sp, maxLines = 2)
            Main400Text(text = item.createdAt, color = Color.LightGray, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
            Main400Text(text = item.content, color = Color.Black, fontSize = 16.sp, maxLines = Int.MAX_VALUE)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CodePostListItemComponentPrev() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        CodePostListItemComponent(
            item = DummyCodePostListItem,
            onClick = {},
            modifier = Modifier
        )
    }
}