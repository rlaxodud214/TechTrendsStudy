package com.example.techtrendsstudy

import android.os.Bundle
import android.widget.ImageButton
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
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
            // 상태값 저장에선 remember보단 rememberSaveable를 기본적으로 많이 쓰게 될 듯
            // rememberSaveable : 화면 회전 및 환경구성 변경에도 상태 보존(임시적인 상태 저장으로 내장 DB를 대체할 수는 없음)
            // isFavorite의 자료형 : MutableState<Boolean>
            var isFavorite by rememberSaveable{
                mutableStateOf(false)
            }
            TechTrendsStudyTheme {
                ImageCard(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(16.dp),
                    isFavorite = isFavorite,
                ) {
                    favorite -> isFavorite = favorite
                }
            }
        }
    }
}

// Jetpack Compose 5 - Image, Card, State
@Composable
fun ImageCard( // 커스텀 UI 만들기 - RN과 똑같음
    modifier: Modifier = Modifier, // Default Value 지정
    isFavorite: Boolean, // 변수x -> 상수
    onTabFavorite: (Boolean) -> Unit,
) {
    // 기존 CardView
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp), // 카드 외각라인 둥글게하는 부분
        elevation = CardDefaults.cardElevation(5.dp), // 외각라인 그림자 반영
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            // 로컬의 이미지 파일을 출력할 때는 Painter를 매개 값으로 사용한다.
            // 기존 ImageView
            Image(
                painter = painterResource(id = R.drawable.poster),
                contentDescription = null, // content 설명 텍스트 필수로 넣어야 함 -> null 허용
                // ContentScale의 속성 ref : https://developer.android.com/jetpack/compose/graphics/images/customize?hl=ko
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd,
            ) {
                IconButton(onClick = {
                    onTabFavorite.invoke(!isFavorite)
                }) {
                    Icon(
                        imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}
