package com.d8corp.d8amelihovstest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit
) {
    Box(modifier = modifier.padding(start = 10.dp, end = 10.dp)) {
        val text: MutableState<String> = remember { mutableStateOf("") }
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChange.invoke(it)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            label = { Text("Search") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Composable fun ListOfData(list: MutableState<List<ListItem>>) = LazyColumn {
    val items = list.value
    items(items.size) {
        Item(item = items[it])
    }
}

@Composable fun Item(item: ListItem) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp)
        .background(
            color = Color(0xFFCDE8E1),
            shape = RoundedCornerShape(20.dp),
        )
        .padding(all = 10.dp),
) {
    Image(
        painter = painterResource(id = R.drawable.flower),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    )
    Column(
        modifier = Modifier
            .padding(16.dp)
            .align(alignment = Alignment.CenterVertically)
            .fillMaxWidth()
    ) {
        Text(
            text = item.title,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = item.subTitle,
            color = Color.Black
        )
    }
}

@Composable
fun FAB(onClick: () -> Unit) = Box(modifier = Modifier.fillMaxSize(1.0f)) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .padding(12.dp)
            .align(Alignment.BottomEnd)
    ) {
        SmallFloatingActionButton(onClick = onClick) {
            Icon(Icons.Filled.MoreVert, "Most used symbols.")
        }
    }
}

@Composable fun Pager(pagerState: PagerState) = HorizontalPager(
    modifier = Modifier,
    state = pagerState,
    pageSpacing = 20.dp,
    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp),
    ) { page -> Image(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.flower),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
}

@Composable fun PageIndicator(size: Int, pagerState: PagerState) = LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
    repeat(size) { iteration ->
        val color = if (pagerState.currentPage%size == iteration) Color.Blue else Color.LightGray
        item(key = "item$iteration") {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(color, CircleShape)
                    .size(10.dp)
            )
        }
    }
}

@Composable fun DialogPosition(position: Int) = Text(
    text = "List: $position",
    color = Color.Black,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)

@Composable fun DialogOccurency(char: Char, occurency: Int) = Text(
    text = "${char}: ${occurency}",
    fontSize = 20.sp,
    color = Color.Black
)