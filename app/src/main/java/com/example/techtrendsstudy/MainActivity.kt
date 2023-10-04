package com.example.techtrendsstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.techtrendsstudy.ui.theme.TechTrendsStudyTheme

// JetPack Compose 및 MVVM 디자인 패턴 학습
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent 안에서만 Compose 객체를 사용할 수 있다.
        setContent {
            val scrollState = rememberScrollState()
            TechTrendsStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    /* Jetpack Compose 4 - List(기존 ListView), LazyColumn(기존 RecyclerView)
                       데이터의 개수가 많을 때, 많은 리소스가 발생하므로 LazyColumn를 사용한다.

                       LazyColumn(
                           modifier: Modifier = Modifier,
                           state: LazyListState = rememberLazyListState(),
                           contentPadding: PaddingValues = PaddingValues(0.dp),
                           reverseLayout: Boolean = false,
                           verticalArrangement: Arrangement.Vertical =
                               if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
                           horizontalAlignment: Alignment.Horizontal = Alignment.Start,
                           flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
                           userScrollEnabled: Boolean = true,
                           content: LazyListScope.() -> Unit
                    */
                    LazyColumn(
                        modifier = Modifier
                            .background(color = Color.Green)
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp), // 외부 객체 와의 패딩
                        verticalArrangement = Arrangement.spacedBy(4.dp), // 내부 객체 간의 패딩
                    ) {
                        item {
                            Text("Header")
                        }
                        /*
                        items(
                            count: Int,
                            key: ((index: Int) -> Any)? = null,
                            contentType: (index: Int) -> Any? = { null },
                            itemContent: @Composable LazyItemScope.(index: Int) -> Unit

                         */
                        items(100) { index ->
                            Text("List 유사 객체[$index]")
                        }
                        item {
                            Text("Footer")
                        }
                    }
                    // 아래의 Column을 위와 같이 LazyColumn으로 변경
//                    Column(modifier = Modifier
//                        .background(color = Color.Green)
//                        .fillMaxWidth()
//                        .verticalScroll(scrollState)
//                    ) {
//                        for (i in 1..50) {
//                            Text("$i")
//                        }
//                    }
                }
            }
        }
    }
}