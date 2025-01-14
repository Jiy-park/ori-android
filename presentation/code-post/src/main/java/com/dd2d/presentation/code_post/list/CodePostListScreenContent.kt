package com.dd2d.presentation.code_post.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.core.presentation.refresh_lazy.RefreshLazyColumn
import com.dd2d.core.presentation.refresh_lazy.RefreshLazyListState
import com.dd2d.core.presentation.theme.MainColor
import com.dd2d.core.presentation.theme.MainGrey
import com.dd2d.domain.code_post.model.CodePostListItem
import com.dd2d.domain.code_post.model_dummy.DummyCodePostListItem
import com.dd2d.presentation.code_post.list.component.CodePostListItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CodePostListScreenContent(
    state: RefreshLazyListState,
    list: SnapshotStateList<CodePostListItem>,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onSearch: (String) -> Unit,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current

    var searchText by remember { mutableStateOf("") }

    Surface(
        color = MainGrey,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    trailingIcon = {
                        IconButton(
                            onClick = { onSearch(searchText) }
                        ) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    },
                    shape = RoundedCornerShape(15.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions {
                        keyboard?.hide()
                        onSearch(searchText)
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,

                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,

                        selectionColors = TextSelectionColors(
                            handleColor = MainColor,
                            backgroundColor = MainColor.copy(alpha = 0.4f)
                        ),
                        cursorColor = MainColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            RefreshLazyColumn(
                onLoad = onLoadMore,
                onRefresh = onRefresh,
                refreshLazyListState = state,
                contentPadding = PaddingValues(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = list,
                    key = CodePostListItem::id
                ) { item ->
                    CodePostListItemComponent(
                        item = item,
                        onClick = { onItemClick(item.id) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun CodePostListScreenContentPrev() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CodePostListScreenContent(
            state = RefreshLazyListState.Idle,
            list = List(30) { DummyCodePostListItem.copy(id = it) }.toMutableStateList(),
            onRefresh = {},
            onLoadMore = {},
            onSearch = {},
            onItemClick = {},
            modifier = Modifier
        )
    }
}