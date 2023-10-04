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
                    // 모든 Composable 객체들은 기본적으로 modifier를 사용할 수 있다.
                    //
                    Box(modifier = Modifier)
                }
            }
        }
    }
}

/*
[GPT Q&A] Android에서 사용하는 기본적인 Composable 객체들을 나열해줘
Jetpack Compose는 Android의 UI 툴킷으로, 선언적 UI를 구축하기 위한 현대적인 방식을 제공합니다.
아래는 Android Jetpack Compose에서 사용하는 기본적인 Composable 객체들을 나열한 것입니다

1. Box: 다양한 컨텐츠를 포함할 수 있는 단순한 컨테이너.
2. Column: 세로로 자식 Composables을 배열하는 컨테이너.
3. Row: 가로로 자식 Composables을 배열하는 컨테이너.
4. Text: 텍스트를 표시하는 Composable.
5. Image: 이미지를 표시하는 Composable.
6. Button: 사용자 입력을 처리하는 버튼 Composable.
7. Surface: UI의 머티리얼 디자인 표면을 표현.                                           - check
8. Scaffold: 기본적인 앱 구조를 제공하는 Composable.                                    - 상단 앱바, 바텀바, FAB, 드로어 등을 매개값으로 가짐
9. LazyColumn & LazyRow: 대량의 데이터나 아이템을 가로나 세로로 효율적으로 표시하는데 사용.   - 음원리스트나 많은 개수를 리소스를 올려야할 때 사용
10. Slider: 값의 범위 내에서 슬라이더를 통해 선택을 허용.
11. Checkbox: 체크 박스 UI를 제공.
12. RadioButton: 라디오 버튼 UI를 제공.
13. Switch: 스위치 토글 UI를 제공.
14. TextField: 사용자 입력을 위한 텍스트 필드 Composable.
15. Card: Material 디자인의 카드 UI를 표현하는 Composable.
16. Icon: 아이콘을 표시하기 위한 Composable.
17. TopAppBar: 상단의 앱바 UI를 표현.
18. BottomNavigation: 하단 탭 네비게이션을 위한 Composable.
19. Divider: UI 내 구분선을 표현.
20. ModalDrawerLayout: Drawer UI를 위한 Composable.                                  - check
21. FloatingActionButton: FAB(Floating Action Button)을 위한 Composable.             - Icon 및 Label을 포함한 버튼 UI + 애니메이션
22. ProgressBar: 로딩 상태를 표현하는 ProgressBar.
23. BackdropScaffold: Backdrop UI 구조를 제공.                                        - 상단 영역을 스크롤 시 접히게 할 수 있음
24. AlertDialog: 알림 대화상자를 표현하는 Composable.
25. LaunchedEffect: 부수 효과와 함께 Composable을 재시작.                               - 언제 필요할까? -> 비동기 네트워크 호출 시 사용 / 데이터 초기화: 처음 Composable이 구성될 때 데이터를 로드하거나 초기화하려는 경우, 부수 효과: 특정 조건이 충족될 때 Composable 외부에 영향을 주는 작업을 수행해야 할 때, 자동 취소: Composable이 재구성되거나 사라질 때 관련된 비동기 작업을 자동으로 취소하려는 경우.
26. BottomAppBar: 하단의 앱바 UI를 표현.
27. BottomSheetScaffold: 하단 시트와 관련된 Scaffold.
28. DropdownMenu: 드롭다운 메뉴 UI.
29. CircularProgressIndicator: 원형 로딩 인디케이터.
30. LinearProgressIndicator: 선형 로딩 인디케이터.
31. ModalBottomSheetLayout: 모달하단 시트 UI.
32. Spacer: 공간을 만드는 Composable.
33. Tabs: 탭 레이아웃을 위한 Composable.
34. Popup: 임시 UI를 위한 팝업 Composable.
35. ScrollableColumn: 스크롤 가능한 Column.
36. ScrollableRow: 스크롤 가능한 Row.
37. ViewPager: 페이지 간 슬라이드를 위한 뷰 페이저.                                       - 스와이프 기능 구현시 사용
38. Snackbar: 임시 메시지를 표시하기 위한 Snackbar UI.
39. AnnotatedString: 하나의 Text 안에서 여러 스타일을 적용할 수 있도록 하는 정보를 담은 객체  - Text Line 중간에 다른 객체(이미지, 아이콘 등)를 넣을 수 있음
40. AnimatedVisibility: 특정 조건에 따라 Composable의 표시 여부를 애니메이션과 함께 변경.   - 비동기 네트워크 호출 시 사용
41. LazyVerticalGrid & LazyHorizontalGrid: 그리드 형태로 아이템을 효율적으로 표시하는데 사용.
42. CollapsingToolbarLayout: 스크롤 시 축소되는 툴바.                                   - 상단 영역을 스크롤 시 접히게 할 수 있음
43. ConstraintLayout: 유연한 레이아웃 제약 조건을 제공하여 복잡한 UI를 구성.
44. Crossfade: 두 Composable 사이의 전환 효과를 위한 애니메이션.                          - 비동기 네트워크 호출 시 사용
45. NavigationDrawer: 네비게이션 드로어 UI를 위한 Composable.
46. BoxWithConstraints: 부모의 제약 조건 내에서 자식 Composable을 배치.
47. Canvas: 저수준 그래픽 작업을 위한 Composable.                                       - 개발도상국 사람을 타겟으로 할 때 사용할 수 있을 듯
48. CoilImage & GlideImage: 외부 이미지 로딩 라이브러리를 사용하여 이미지를 표시.           - check
49. ExperimentalAnimationApi: 실험적인 애니메이션 기능.                                 - check
*/