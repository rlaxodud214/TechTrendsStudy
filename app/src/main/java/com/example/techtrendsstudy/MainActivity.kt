@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.techtrendsstudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/*  명령어 학습
    1. CA + l : 자동 정렬
    2. CA + o : import 정리
    3. S + F6 : 이름 한 번에 수정
    4. C + F12 : 파일 구조 확인
    5. C + E : 최근 작업 파일 목록(CA L or R도 가능)
    6. CS + BackSpace : 최근 수정 위치
    7. S 2번 : 모든 프로젝트 파일에서 찾기
    8. // TODO키워드 : 에러처리 해야할 내용 -> 작업하다가 중간중간에 나중에 수정해야할 부분 체크 가능!!!
    9. Git에 업로드 안된 내용이라도 Local History를 통해서 파일 단위로 코드 복구가 가능하다.
*/

// JetPack Compose 및 MVVM 디자인 패턴 학습
// Jetpack Compose 7 - Navigation
@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "first"
            ) {
                composable("first") {
                    FirstScreen(navController = navController)
                }
                composable("second") {
                    SecondScreen(navController = navController)
                }
                // backStackEntry를 통해서 value 값을 얻어올 수 있다.
                composable("third/{value}") { backStackEntry ->
                    ThirdScreen(
                        navController = navController,
                        value = backStackEntry.arguments?.getString("value") ?: "",
                    )
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("첫 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("second")
        }) {
            Text("두 번째!")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if (value.isNotEmpty()) {
                // 화면 전환시 데이터 전달 방법!!
                navController.navigate("third/$value")
            }
        }) {
            Text("세 번째!")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("두 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            /* Q. 아래 코드 둘 다 같은 동작을 한다? -> https://developer.android.com/reference/androidx/navigation/NavController#navigateUp()
            navController.navigateUp() : Up버튼 실행 -> 스택에 아무 화면도 남아있지 않을 때, 새로운 화면을 생성하는 이슈 있음(이 경우 앱 종료 안됨)
            navController.popBackStack() : Back버튼 실행 -> 백스택 항목이 없으면 아무 작업도 수행하지 않음. 따라서 안정적인 popBackStack을 사용할 것
            */
            navController.navigateUp()
        }) {
            Text("뒤로 가기!")
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController, value: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("세 번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text("$value")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("뒤로 가기!")
        }
    }
}