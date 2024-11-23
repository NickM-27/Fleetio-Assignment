package com.nick.mowen.fleetio.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}

@Composable
fun <T> LazyLoadingColumn(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    canLoadMore: Boolean,
    listState: LazyListState = rememberLazyListState(),
    listItems: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
    loadingItem: @Composable () -> Unit,
    loadMore: () -> Unit
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !loading && canLoadMore) loadMore()
    }

    LazyColumn(modifier = modifier, state = listState) {
        items(
            items = listItems,
            key = { item: T -> itemKey(item) },
        ) { item ->
            itemContent(item)
        }
        if (loading) {
            item {
                loadingItem()
            }
        }
    }
}