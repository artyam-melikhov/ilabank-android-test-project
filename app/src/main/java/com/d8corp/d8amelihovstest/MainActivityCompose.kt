package com.d8corp.d8amelihovstest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.d8corp.d8amelihovstest.data.Data

class MainActivityCompose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { MainActivity(Data.dataset) }
    }

    @Preview @Composable fun Preview() {
        MainActivity(Data.dataset)
    }

    @Composable  fun MainActivity(dataset: ArrayList<List<ListItem>>) = Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val TopBarHeight = 290.dp
        val SearchBarHeight = 230
        val position: MutableState<Int> = remember { mutableStateOf(0) }
        val list: MutableState<List<ListItem>> = remember { mutableStateOf(dataset[0]) }

        val appBarMaxHeightPx = with(LocalDensity.current) { TopBarHeight.roundToPx() }
        val connection = remember(appBarMaxHeightPx) {
            CollapsingCarouselNestedScrollConnection(appBarMaxHeightPx)
        }

        val density = LocalDensity.current
        val spaceHeight by remember(density) {
            derivedStateOf {
                with(density) {
                    (appBarMaxHeightPx + connection.topBarOffset + SearchBarHeight).toDp()
                }
            }
        }

        Box(Modifier.nestedScroll(connection)) {
            Column {
                Spacer(Modifier.padding(4.dp).height(spaceHeight))

                ListOfData(list)
            }

            Column(modifier = Modifier.offset { IntOffset(0, connection.topBarOffset) }) {
                val pagerState = rememberPagerState(
                    pageCount = { Int.MAX_VALUE },
                    initialPage = Int.MAX_VALUE / 2 - ((Int.MAX_VALUE / 2) % dataset.size)
                )

                Pager(pagerState)
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        position.value = page % dataset.size
                        list.value = dataset[position.value]
                    }
                }

                Spacer(Modifier.height(5.dp))

                PageIndicator(dataset.size, pagerState)

                Spacer(Modifier.height(5.dp))

                SearchBar { filter ->
                    list.value = dataset[position.value].filter { filter.isEmpty() || it.title.contains(filter) }.toList()
                }
            }
        }

        FAB {
            BottomDialog(list.value, position.value).show(supportFragmentManager, "btm_dlg_err_no_internet")
        }
    }
}