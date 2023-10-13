package com.example.techtrendsstudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

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
// Jetpack Compose 9 - State 심화
class MainActivity : ComponentActivity() {
    // private val viewModel by viewModels<MainViewModel>()
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()) {
    val value = viewModel.data.value // viewModel의 getter

    val text3: State<String> = viewModel.liveData.observeAsState("") // LiveData to State

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(value)
        /* 리컴포지션
           정의 : 특정 조건 또는 상태 변경에 따라 해당 UI를 재구성하는 과정
           -> Q : 해당 UI란 TextField만을 재구성 하는 것인지 아니면 / 전체 HomeScreen을 재구성 하는 것인지 궁금하다.
           -> A : 관련 Composable 함수가 리컴포지션 되어 UI가 업데이트 되지만, 여기서 전체 "HomeScreen"이 리렌더링 되는 것이 아니다!
                  변경된 상태를 "사용하는" 부분만 재구성된다. -> 성능 최적화를 위해 필요한 최수한의 부분만 업데이트 된다.
              I : React Native에선 전체가 리렌더링 됐었는데, Jetpack Compose는 그렇지 않아 성능 면에서 엄청난 장점인 것 같다!!
         */
        TextField(
            value = value,
            onValueChange = { newValue ->
                viewModel.updateValue(newValue)
            })
        Button(
            onClick = {
                viewModel.updateValue("")
            }) {
            Text("초기화")
        }
    }
}

// ViewModel
class MainViewModel : ViewModel() {
    private val _data = mutableStateOf("")
    val data: State<String> = _data
    fun updateValue(newValue: String) {
        _data.value = newValue
    }

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData
}
