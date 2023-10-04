package com.example.techtrendsstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
                    /*
                    modifier를 통해서 백그라운드 색상, 패딩, 마진, 크기 등을 조정할 수 있음
                    public inline fun Column(
                        modifier: Modifier,
                        verticalArrangement: Arrangement.Vertical,
                        horizontalAlignment: Alignment.Horizontal,
                        content: @Composable() (ColumnScope.() -> Unit)
                    ): Unit
                    */
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Blue)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Text("Hello")
                        // 컴포넌트 간의 간격은 Spacer를 통해 조절할 수 있다.
                        Spacer(Modifier.width(16.dp))
                        Text("World")
                    }
                }
            }
        }
    }
}