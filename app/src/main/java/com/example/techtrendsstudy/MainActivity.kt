package com.example.techtrendsstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.techtrendsstudy.ui.theme.TechTrendsStudyTheme

// JetPack Compose 및 MVVM 디자인 패턴 학습
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent 안에서만 Compose 객체를 사용할 수 있다.
        setContent {
            TechTrendsStudyTheme {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp), // 외부 객체 와의 패딩
                    verticalArrangement = Arrangement.spacedBy(4.dp), // 내부 객체 간의 패딩
                ) {
                    item {
                        ImageCard(ContentScale.Crop) // 아래-위 잘림
                    }
                    item {
                        ImageCard(ContentScale.Fit) // 원본 이미지 비율을 객체 크기에 맞게 수정
                    }
                    item {
                        ImageCard(ContentScale.FillBounds) // Fit에서 여백 없게 확장
                    }
                }
            }
        }
    }
}

// Jetpack Compose 5 - Image, Card, State
@Composable
fun ImageCard(cs : ContentScale) {
    /* 기존 CardView
    Card(
        modifier: Modifier = Modifier,
        shape: Shape = CardDefaults.shape,                       // 외각라인 둥글게
        colors: CardColors = CardDefaults.cardColors(),
        elevation: CardElevation = CardDefaults.cardElevation(), // 그림자
        border: BorderStroke? = null,
        content: @Composable ColumnScope.() -> Unit
   */

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp), // 카드 외각라인 둥글게하는 부분
        elevation = CardDefaults.cardElevation(5.dp), // 외각라인 그림자 반영
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            // 로컬의 이미지 파일을 출력할 때는 Painter를 매개 값으로 사용한다.
            /* 기존의 ImageView와 같음
            Image(
                painter: Painter,
                contentDescription: String?,
                modifier: Modifier = Modifier,
                alignment: Alignment = Alignment.Center,
                contentScale: ContentScale = ContentScale.Fit,
                alpha: Float = DefaultAlpha,
                colorFilter: ColorFilter? = null
             */
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = "poster", // content 설명 텍스트 필수로 넣어야 함.
                contentScale = cs, // https://developer.android.com/jetpack/compose/graphics/images/customize?hl=ko
            )
        }
    }
}
