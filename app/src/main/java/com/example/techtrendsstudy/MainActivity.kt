package com.example.techtrendsstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

        setContent {
            TechTrendsStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    /* 모든 Composable 객체들은 기본적으로 modifier를 사용할 수 있다.
                       Box는 자식들이 다 겹치는 속성을 가지고 있어 FrameLayout과 유사하다.
                       Box(
                           modifier: Modifier = Modifier,
                           contentAlignment: Alignment = Alignment.TopStart,
                           propagateMinConstraints: Boolean = false,
                           content: @Composable BoxScope.() -> Unit
                       Column과 Row, Box를 사용하여 큰 틀의 레이아웃을 구성하면 된다. -> RN, 플러터와 같음
                     */
                    Box(modifier = Modifier
                        .background(color = Color.Gray)
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text("TopStart")
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomEnd) {
                            Text("BottomEnd")
                        }
                    }
                }
            }
        }
    }
}