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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
/* 1. Column, Row, Text | 2 - Composable, Preview
   Composable 함수 이름은 대문자로 시작해야한다.
*/
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

// Priview로 선언하면 Hot Relod가 가능하다.
@Preview(showBackground = true)
@Composable
fun DefalutPreview() {
    TechTrendsStudyTheme {
        Greeting("김태영")
    }
}