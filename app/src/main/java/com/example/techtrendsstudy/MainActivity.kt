package com.example.techtrendsstudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
// Jetpack Compose 8 - ViewModel
class MainActivity : ComponentActivity() {
    // private val viewModel by viewModels<MainViewModel>()
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // remember를 사용하면 외부 요인으로 인해 UI가 다시 그려질 때마다 상태를 잃지 않게 해준다.
            // 또는 remember가 없는 상태에서 UI가 다시 그려지더라도 상태를 유지할 수 있게 ViewModel을 사용해도 된다.
            // val data = remember { mutableStateOf("Hello") }
            val viewModel by viewModels<LikeCountViewModel>()
            LikeCountScreen(viewModel)

        }
    }
}

@Composable
fun LikeCountScreen(viewModel: LikeCountViewModel) {
    val data: Int by viewModel.counter.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            data.toString(),
            fontSize = 30.sp
        )
        Button(onClick = {
            viewModel.incrementCounter()
        }) {
            Text("Up")
        }
    }
}

class LikeCountViewModel : ViewModel() {
    // 외부에서 직접적인 데이터 수정을 막기 위해 private으로 정의한 뒤, getter-setter 사용
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> get() = _counter.asStateFlow() // 읽기 전용으로만 공개 (getter)
    fun incrementCounter() { // 수정할 수 있는 기능은 메소드로 제공 (setter)
        _counter.value++
        // DB 업데이트 및 다른 사용자에게 알림?
    }
}

/* [GPT 및 Google Q&A] MutableStateOf와 MutableStateFlow의 차이점은?
    1. 목적
       Of : UI의 상태를 선언적으로 관리하기 위함 - 일반적인 경우에 사용
       Flow : Coroutine의 Flow를 사용하기 위함, 상태를 감시 가능한 스트림으로 관리하기 위함 - 주요 기능에 사용
    2. 동작 방식
       Of : 해당 상태를 참조하는 Composable 함수는 상태가 변경될 때마다 다시 실행
       Flow : Hot Flow로, 상태가 변경될 때마다 해당 변경 사항을 구독하고 있는 모든 구독자에게 방출 => 옵저버 패턴!!
    3. 사용 장소
       Of : 주로 Composable 함수 내에서 상태를 정의하고 관리하는 데 사용
       Flow : ViewModel 또는 다른 데이터 관리 클래스에서 상태를 관리하고 여러 구성 요소에 상태의 변경을 방출하는 데 사용
    4. Thread Safety
       Of : thread-safe 보장은 안되지만, Compose에서는 주로 메인 쓰레드에서 사용되므로 오류 확률 낮음
       Flow : thread-safe 보장할 수 있음, 여러 쓰레드에서 동시에 접근하고 수정해도 안전하다.
*/

/*
    [GPT Q&A] ViewModel
    1. 정의 : Android의 Architecture Components 중 하나로, UI 관련 데이터를 관리 하는데 사용
    2. 사용 목적 : 액티비티나 프래그먼트가 다시 생성되더라도 UI 데이터를 보존하기 위함 -> 데이터의 일관성 유지
    3. When - 언제 사용할까? or 사용하면 좋을까?
       1) 데이터 캐싱 : 서버에서 데이터를 불러온 뒤 일정 시간 동안 임시로 저장하고 싶을 때 -> 화면 간의 데이터 전달이나 재요청을 최소화 할 수 있음
       2) LiveData나 StateFlow와의 연동 : Compose의 collectAsState와 같은 메소드를 사용하여 쉽게 Composable과 연결할 수 있음,
                                         UI와 데이터의 상태를 쉽게 연결하고 동기화할 수 있음
       3) 데이터 처리 : 데이터를 읽어와서 UI에 필요한 형태로 처리하는 로직을 ViewModel 안에서 구현
       4) 사이드 이펙트 관리 : 사용자 입력의 이벤트를 처리하고, 그 결과로 발생하는 사이드 이펙트(DB Update, 네트워크 요청)를 관리하는 데 적합함
       5) UI 이벤트 처리 : Composable에서 발생하는 이벤트를 'ViewModel'로 전달해서 그에 따른 액션을 수행 또는 상태 변경
       6) UI 로직과 업무? 로직을 분리하여 이후 코드의 유지보수와 테스트가 용이해진다.

    Tip 1. private로 접근 제어를 하는 것은 객체 지향 프로그래밍에서 중요한 개념 중 하나이다.
           클래스의 내부 상태를 외부에서 수정하도록 허용하면, 예기치 않은 오류로 크래시가 발생할 수 있다.
           이때 setter를 사용하면 데이터의 유효성 검사나 추가적인 로직 실행 등을 통해 데이터의 안정성을 보장할 수 있음
    Tip 2. 데이터 변경 시 다른 메소드를 호출 해야할 때, 직접적인 접근을 허용하면 타이밍에 따라 오류가 발생할 수 있음
    Tip 3. 내부 상태를 private로 유지하고 제어된 방식으로만 변경할 수 있도록 설계하는 것이 바람직하다!

    ex)
    @Composable
    fun GreetingScreen(viewModel: GreetingViewModel = viewModel()) {
        val greeting: String by viewModel.greeting.collectAsState()

        Text(text = greeting)

        Button(onClick = { viewModel.updateGreeting() }) {
            Text("Update Greeting")
        }
    }

    class GreetingViewModel : ViewModel() {
        private val _greeting = MutableStateFlow("Hello, Compose!")
        val greeting: StateFlow<String> get() = _greeting

        fun updateGreeting() {
            _greeting.value = "Hello from ViewModel!"
        }
    }
*/

// ViewModel 클래스를 상속받아 새로운 ViewModel 생성
//class MainViewModel : ViewModel() {
//    // 외부에서 직접적인 데이터 수정을 막기 위해 private으로 정의한 뒤,
//    private val _data = mutableStateOf("Hello")
//
//    val data: State<String> = _data // 읽기 전용으로만 공개 (getter)
//    fun changeValue(value : String) { // 수정할 수 있는 기능은 메소드로 제공 (setter)
//        _data.value = value
//    }
//}