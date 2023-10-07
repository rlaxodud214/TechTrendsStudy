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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

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
// Jetpack Compose 6 - Scaffold, TextField, Button, 구조분해, SnackBar, 코루틴 스코프
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /* 코틀린 구조분해 기법
            interface MutableState<T> : State<T> {
                override var value: T
                operator fun component1(): T
                operator fun component2(): (T) -> Unit
            }
                (value, component1()) 형태로 객체의 프로퍼티에 따라 분해해서 가져왔기 때문에 .value가 필요없음
            */
            val (textValue, setValue) = rememberSaveable {
                mutableStateOf("")
            }

            // val scaffoldState = rememberScaffoldState() // deprecated
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            // Scaffold에서 SnackBar를 사용할 수 있다.
            // https://developer.android.com/jetpack/compose/components/snackbar
            // Scaffold의 파라미터에서 scaffoldState가 제거됨 -> SnackbarHost 사용하기
            Scaffold(
                // scaffoldState = scaffoldState, // deprecated
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = textValue,
                        onValueChange = setValue,
                    )
                    Spacer(Modifier.height(4.dp))
                    Button(onClick = {
                        scope.launch {
                            // showSnackbar처럼 Suspend 키워드가 붙은 함수는 코루틴 스코프 내에서 호출되어야 한다.
                            snackbarHostState.showSnackbar(
                                message = "Snackbar - Hello. $textValue",
                                actionLabel = "OK",
                                // Indefinite : 명시적으로 해제하거나 "actionLabel"을 클릭할 때까지 스낵바를 무기한 표시
                                duration = SnackbarDuration.Indefinite // Defaults to SnackbarDuration.Short
                            )
                        }
                    }) {
                        Text("클릭!!")
                    }
                }
            }
        }
    }
}
