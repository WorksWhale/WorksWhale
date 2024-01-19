<h1 align="center">
<img width="80px" src="https://github.com/WorksWhale/WorksWhale/assets/80674868/00fcc37c-0846-4b55-8b45-9c6d75f2f1eb" alt="" />
 WorksWhale
</h1>

> 업무용 연락처임을 나타내면서 시원한 바다(파랑)의 느낌을 주기 위해 '고래'를 이름에 추가했다.
> 
> 앱의 목적은 업무용 연락처를 부서별로 저장하고 전화나 메세지 기능을 사용할 수 있게 이동하는 것이다.
>
> 개발 기간 : 24/01/15 ~ 24/01/19

<br/>

<p align ="center">
 <img alt="" src ="https://github.com/WorksWhale/WorksWhale/assets/80674868/f84d930c-a113-4dec-b2eb-31f3d42f9423" width="130" heigth="60" />
 <img alt="" src ="https://github.com/WorksWhale/WorksWhale/assets/80674868/d122fec5-a8e1-48b6-8acd-bf69241f0d57" width="130" heigth="60" />
 <img alt="" src ="https://github.com/WorksWhale/WorksWhale/assets/80674868/c4515c1d-72df-4ce8-9751-39494b95b087" width="130" heigth="60" />
 <img alt="" src ="https://github.com/WorksWhale/WorksWhale/assets/80674868/fbad6903-a132-47f9-bcc8-96758cde4c11" width="130" heigth="60" />
 <img alt="" src ="https://github.com/WorksWhale/WorksWhale/assets/80674868/49fffee5-1109-4637-92fd-356c2865b3b1" width="130" heigth="60" />
</p>

<br/>

## 👨‍💻 프로젝트 팀원
한병철

- 깃허브 : <https://github.com/rihan530>

최성진

- 깃허브 : <https://github.com/CodeNewbieee>

오성원

- 깃허브 : <https://github.com/OhSeongWon>

박희수

- 깃허브 : <https://github.com/heesoo-park>

<br/>

## 🧭 와이어 프레임
최종 결과물과는 차이가 존재한다.

<Figma 링크>

<p align="center">
  <a href="https://www.figma.com/file/O4cof7WVCxbgDMRaralSyk/%EC%88%99%EB%A0%A8-%ED%8C%80%EA%B3%BC%EC%A0%9C-7%EC%A1%B0?type=design&node-id=0-1&mode=design&t=mckZYNV20nst6dpF-0">
    <img alt="와이어프레임" src="https://github.com/WorksWhale/WorksWhale/assets/80674868/411f98e5-73ac-4171-ae95-8a3386dbd758">
</p>

<br/>

## 🐱‍🐉 역할분담
한병철 : 상세 정보 레이아웃 / 상세 정보의 좋아요 기능, 상세 정보의 메세지 앱 이동 기능, 상세 정보의 전화 앱 이동 기능

최성진 : 연락처 리스트&리사이클러뷰 아이템 레이아웃 / 연락처 리스트의 리사이클러뷰 구현, 타이틀과 목록 뷰타입 2개 구분 및 타이틀 고정, 연락처 리스트의 플로팅 액션 버튼 기능, 연락처 리스트의 검색 기능

오성원 : 내 정보 수정 레이아웃 / 내 정보 수정 다이얼로그 유효성 체크 기능, 연락처 리스트 스와이프 기능

박희수 : 마이 페이지&연락처추가 레이아웃 / 마이 페이지의 상단 버튼 기능, 바텀 탭 기능, 연락처 추가 다이얼로그 유효성 체크 기능, 갤러리에서 이미지 선택 기능, 권한 설정 기능, 뒤로 가기 버튼 기능

<br/>

## 🎞 화면구성
### 1) 메인화면(연락처 리스트)
앱을 실행하고 처음으로 보이는 화면이다.

상단바, 연락처 리스트, 탭바를 볼 수 있다.

![image](https://github.com/WorksWhale/WorksWhale/assets/80674868/f84d930c-a113-4dec-b2eb-31f3d42f9423)
![image](https://github.com/WorksWhale/WorksWhale/assets/80674868/d4115c9f-421f-45d4-be11-0a1395dfda15)


연락처 리스트는 리사이클러뷰로 구현되어 스크롤이 가능하다.

검색 기능이 있어 돋보기 버튼을 누르면 검색창이 열리고 정해진 조건(이름, 전화번호)에 맞는 연락처 정보를 보여준다.

또한 연락처를 클릭하여 연락처 상세 페이지로 넘어갈 수 있다.

연락처를 누른 상태에서 왼쪽에서 오른쪽으로 스와이프를 하면 삭제 버튼이 생기고 클릭하면 해당 연락처를 삭제할 수 있다.

탭바는 뷰페이저와 연동되어 탭 아이템 클릭이나 뷰페이저 공간을 스와이프하는 것으로 연락처 리스트와 마이 페이지를 이동할 수 있다.

<br/>

### 2) 메인화면(마이 페이지)
연락처 페이지에서 탭 아이템 클릭이나 뷰페이저 공간을 스와이프하는 것으로 볼 수 있는 화면이다.

자신의 정보(사진, 이름, 전화번호, 이메일)를 볼 수 있고, 수정할 수 있다.

![image](https://github.com/WorksWhale/WorksWhale/assets/80674868/d122fec5-a8e1-48b6-8acd-bf69241f0d57)

해당 정보들은 앱을 삭제하기 전까지 지워지지 않는다.

또한 내 정보 수정 다이얼로그에서 입력한 값이 마이 페이지에 바로 반영된다.

<br/>

### 3) 상세 페이지
연락처 리스트에서 특정 연락처를 클릭하여 볼 수 있는 화면이다.

이름, 전화번호, 부서, 이메일, 메모, 알람을 볼 수 있다.

![image](https://github.com/WorksWhale/WorksWhale/assets/80674868/c4515c1d-72df-4ce8-9751-39494b95b087)

스크롤뷰로 구현되어 스크롤이 가능하다.

좋아요 버튼을 눌러 특정 인물을 표시할 수 있고

메세지 버튼을 눌러 해당 전화번호로 보내는 메세지 앱으로 이동할 수 있고

전화 버튼을 눌러 해당 전호번호로 전화를 걸 수 있는 전화 앱으로 이동할 수 있다.

<br/>

### 4) 연락처 등록 다이얼로그
연락처 리스트에 있는 플로팅 액션 버튼을 클릭하면 나타나는 다이얼로그다.

프로필 이미지, 이름, 전화번호, 부서, 이메일, 메모를 작성하고 버튼을 통해 알림 설정할 수 있다.

![image](https://github.com/WorksWhale/WorksWhale/assets/80674868/fbad6903-a132-47f9-bcc8-96758cde4c11)

사진은 이미지 영역을 클릭하면 나오는 갤러리에서 선택하여 가져온다.

알림 설정을 OFF가 아닌 다른 걸 선택하면 알림 설정을 했다는 토스트 메세지가 출력된다.

정해둔 시간이 되면 알림이 오고 알림에 있는 액션버튼을 누를 시 앱이 시작된다.

<br/>

### 5) 내 정보 수정 다이얼로그
마이 페이지에 진입했을 때 나타나는 상단 메뉴를 클릭하면 나타나는 다이얼로그다.

사진, 이름, 전화번호, 이메일을 설정할 수 있다.

![image](https://github.com/WorksWhale/WorksWhale/assets/80674868/49fffee5-1109-4637-92fd-356c2865b3b1)

사진은 이미지 영역을 클릭하면 나오는 갤러리에서 선택하여 가져온다.

<br/>

# 메인 페이지 주요 기능

## 1. TabLayout과 ViewPager2 연동

TabLayout과 ViewPager2를 연동해서 뷰페이저를 스크롤하든지, 탭바를 누르든지 화면(프래그먼트)이 이동되도록 구현했다.
```kotlin
TabLayoutMediator(tabLayoutMainBottom, viewPagerMain) { tab, position ->
    when (position) {
        0 -> tab.text = getString(R.string.contact_list_tab)
        1 -> tab.text = getString(R.string.my_page_tab)
    }
}.attach()
```

## 2. ViewPager의 프래그먼트 변화에 따른 상단 버튼 이미지 변경

뷰페이저의 프래그먼트 변화를 감지하기 위해 뷰페이저의 OnPageChangeCallback 함수를 등록해 그 안에서 onPageSelected 함수를 오버라이딩했다.

OnPageChangeCallback은 뷰페이저를 사용하면서 currentItem을 변경하거나 화면을 스크롤 하여 다른 아이템으로 이동하는 경우에 사용할 수 있는 콜백 함수이다.

그 안의 onPageSelected 함수는 뷰페이저 내에서 프래그먼트가 이동 완료했을 때 호출되고 여기서 UI 갱신이 가능하다.

연락처 리스트에 있을 때는 상단 버튼 이미지가 보이지 않고, 마이 페이지에 있을 때는 보이도록 구현했다.
```kotlin
viewPagerMain.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        when (position) {
            0 -> {
                ivMainMenu.isVisible = false
            }
            else -> {
                ivMainMenu.isVisible = true
            }
        }
    }
})
```

## 3. 알림, 전화 권한 설정

권한을 앱이 처음 설치되고 시작되었을 때 얻기 위해 관련 설정 요구 다이얼로그를 띄워주었다.

따로 만들지는 않았고 sdk 33 이상일 때 쓸 수 있는 requestPermission을 이용했다.
```kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.CALL_PHONE), 0)
}
```

## 4. 뒤로가기 버튼 기능 설정

OnBackPressedCallBack 콜백함수를 사용했다.

SearchView의 포커스가 있을 때는 SearchView를 닫고 포커스가 없을 때는 종료 다이얼로그를 띄우도록 handleOnBackPressed()를 오버라이딩한 후 onBackPressedDispatcher에 등록했다.
```kotlin
private val callback = object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
        if (searchViewFocus) {
            adapter.closeSearchView()
        } else {
            val builder = AlertDialog.Builder(this@MainActivity,
                R.style.MyAlertDialogStyle
            )
            builder.setTitle(getString(R.string.app_name))
            builder.setMessage(getString(R.string.quit_app_dialog_message))
            builder.setIcon(R.drawable.ic_logo_white)
            builder.setCancelable(false)
            val listener = DialogInterface.OnClickListener { dialog, which ->
                finish()
            }
            builder.setPositiveButton(getString(R.string.quit_app_dialog_positive_btn), listener)
            builder.setNegativeButton(getString(R.string.quit_app_dialog_negative_btn), null)
            builder.show()
        }
    }
}
```

또한 상세 페이지에서는 뒤로가기 버튼을 눌렀을 때 현재 프래그먼트를 종료할 수 있도록 구현했다.
```kotlin
callback = object : OnBackPressedCallback(true) {
    //상세정보에서 뒤로가기를 눌렀을 경우 변경된 값을 arguments를 통해서 전달
    override fun handleOnBackPressed() {
        arguments?.getInt(IntentKeys.EXTRA_POSITION, position)?.let { updateLike?.update(it) }
        requireActivity().supportFragmentManager.beginTransaction().remove(this@ContactDetailFragment).commit()
        requireActivity().supportFragmentManager.popBackStack()
    }
}
requireActivity().onBackPressedDispatcher.addCallback(this, callback)
```

- 🚨 **트러블슈팅**

1. 처음 탭 레이아웃과 뷰페이저를 연동할 때 앱을 키자마자 튕기며 에러가 났었다.

이유를 알아보니 레이아웃 xml에 탭 아이템의 아이디가 설정되어있었기 때문이었다.

아마 TabLayoutMediator 함수 자체적으로 탭 아이템을 만들어 뷰 페이저와 연동하기 때문인 것 같다.

2. 권한 설정 SDK 문제

처음에는 프로젝트를 minSdk 31로 시작했는데 권한 설정 관련해서 문제가 해결되지 않아 33으로 올렸다.

최근 앱을 출시할 때는 minSdk를 33으로 한다는 걸 보고 그렇게 바꾸는게 맞다고 생각이 들기도 했다.

3. 상세 페이지에서 전화와 메세지 앱을 갔다 왔을 때 뒤로가기 버튼을 누르면 앱 종료 다이얼로그를 띄우는 현상 발생

이건 onResume이 되면서 등록되었던 콜백함수가 없어져서로 판단하여 onResume() 함수 안에 onAttach()에서 설정한 콜백 함수를 다시 만들고 등록했더니 해결했다.

<br/>

# 연락처 추가 다이얼로그 주요 기능

## 1. 전화번호 자동 하이픈 설정

가장 먼저 레이아웃 xml에서 EditText의 inputType 속성을 phone으로 했다.

```
 <EditText
     android:id="@+id/et_add_contact_phone_number"
     android:layout_width="300dp"
     android:layout_height="wrap_content"
     android:layout_marginHorizontal="20dp"
     android:layout_marginTop="8dp"
     android:backgroundTint="@color/blue"
     android:hint="@string/edit_hint_phone_number"
     android:inputType="phone"
     android:maxLength="13"
     app:layout_constraintEnd_toEndOf="parent"
     app:layout_constraintStart_toStartOf="parent"
     app:layout_constraintTop_toBottomOf="@+id/et_add_contact_name" />
```

그리고 해당 EditText에 addTextChangedListener(PhoneNumberFormattingTextWatcher())를 붙였다.

```kotlin
binding.etAddContactPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
```

- 🚨 **트러블슈팅**

위의 과정을 다 했음에도 자동으로 하이픈이 붙지 않았다.

검색을 통해 알게 된 것은 폰의 시스템 언어에 따라 자동 하이픈이 붙는 방식이 다르다는 것이었다.

기본은 영어, 미국이기 때문에 미국의 전화번호 형식에 맞춰 자동 하이픈이 붙는다.

그래서 한국어, 대한민국으로 바꾸고 다시 앱을 실행하니 우리나라의 전화번호 형식에 맞춰 하이픈이 잘 붙는걸 확인할 수 있었다.

## 2. 유효성 검사

EditText의 내용이 변할 때와 포커스 아웃이 됐을 때 유효성 검사를 하도록 구현

각 EditText에 맞는 유효성 검사를 진행하고 에러 메세지를 반환하는 EditText 확장 함수를 만듬
```kotlin
private fun EditText.setErrorMessage() {
    when (this) {
        binding.etAddContactName -> error = getMessageValidName()
        binding.etAddContactPhoneNumber -> error = getMessageValidPhoneNumber()
        binding.etAddContactEmail -> error = getMessageValidEmail()
        else -> Unit
    }
}
```

<br/>

이름은 비어있는지만 체크한다.
```kotlin
private fun getMessageValidName(): String? {
    val name = binding.etAddContactName.text.toString()
    return when {
        name.isBlank() -> AddContactErrorMessage.EMPTY_NAME
        else -> null
    }?.message?.let { getString(it) }
}
```

<br/>

전화번호는 비어있는지와 010으로 시작하는지, 길이가 충족되었는지를 체크한다.
```kotlin
private fun getMessageValidPhoneNumber(): String? {
    val phoneNumber = binding.etAddContactPhoneNumber.text.toString()
    return when {
        phoneNumber.isBlank() -> AddContactErrorMessage.EMPTY_PHONE_NUMBER
        phoneNumber.startZeroOneZero().not() -> AddContactErrorMessage.INVALID_PHONE_NUMBER
        phoneNumber.length < 13 -> AddContactErrorMessage.INVALID_PHONE_NUMBER_LENGTH
        else -> null
    }?.message?.let { getString(it) }
}
```

010으로 시작하는지 체크하는 함수는 String 확장함수를 만들어 사용했다.
```kotlin
private fun String.startZeroOneZero() = this.length > 3 && this.substring(0..2) == "010"
```

<br/>

이메일은 비어있는지와 이메일 형식에 맞는지를 체크한다.
```kotlin
private fun getMessageValidEmail(): String? {
    val email = binding.etAddContactEmail.text.toString()
    return when {
        email.isBlank() -> AddContactErrorMessage.EMPTY_EMAIL
        email.checkEmailFormat().not() -> AddContactErrorMessage.INVALID_EMAIL
        else -> null
    }?.message?.let { getString(it) }
}
```

이메일 형식을 체크하는 함수는 String 확장함수를 만들어 사용했다.
```kotlin
private fun String.checkEmailFormat(): Boolean {
    return Pattern.matches(ConstValues.EMAIL_VALID_CHECK, this)
}
```

<br/>

부서와 메모, 알람은 유효성 검사가 필요없기 때문에 관련 코드가 존재하지 않는다.

모든 유효성 검사를 통과하여 에러 메세지가 존재하지 않을 때 저장 버튼이 활성화된다.
```kotlin
private fun setAddButtonEnable() {
    binding.btnAddContactAdd.isEnabled = getMessageValidName() == null
            && getMessageValidEmail() == null
            && getMessageValidPhoneNumber() == null
}
````

저장 버튼은 enable을 기준으로 하는 selector가 background 속성에 넣어져있어 true일 때는 blue, false일 때는 light_blue 배경색을 가진다.
```
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_enabled="true">
        <shape android:shape="rectangle">
            <corners android:radius="5dp"/>
            <solid android:color="@color/blue"/>
        </shape>
    </item>

    <item>
        <shape android:shape="rectangle">
            <corners android:radius="5dp"/>
            <solid android:color="@color/light_blue"/>
        </shape>
    </item>
</selector>
```


## 3. 저장 버튼 클릭

object class로 만들어놓은 저장소에 다이얼로그에서 입력한 값이 저장 버튼이 눌렸을 때 저장되어야 했다.

또한 저장소에 데이터가 추가되었음을 리사이클러뷰가 있는 연락처 리스트 프래그먼트에도 알려줘야 했다.

먼저 저장을 위해서는 저장소에 함수를 하나 만들었다.
```kotlin
fun addContact(contact: Contact) {
    when (contact) {
        is Contact.Person -> {
            when (contact.department) {
                0 -> {
                    totalContactList.add(totalContactList.indexOf(Contact.Title(1)), contact)
                }
                1 -> {
                    totalContactList.add(totalContactList.indexOf(Contact.Title(2)), contact)
                }
                2 -> {
                    totalContactList.add(totalContactList.indexOf(Contact.Title(3)), contact)
                }
                3 -> {
                    totalContactList.add(totalContactList.indexOf(Contact.Title(4)), contact)
                }
                4 -> {
                    totalContactList.add(totalContactList.indexOf(Contact.Title(5)), contact)
                }
                5 -> {
                    totalContactList.add(contact)
                }
            }
        }
        else -> Unit
    }
}
```

정한 부서에 저장되고 가장 뒤에 저장될 수 있도록 하기 위해 이렇게 코드를 짰다.

그리고 부서가 int형이기 때문에 스피너 값에서 인덱스를 추출하기 위해 다음과 같은 코드를 사용했다.
```kotlin
var department = 0
departmentList.map { getString(it) }.forEachIndexed { idx, item ->
    if (spinnerAddContact.selectedItem.toString() == item) department = idx
}
```

리소스 아이디가 들어있는 departmentList를 String으로 바꾸고 거기서 스피너와 같은 값인 원소의 인덱스를 가져오는 알고리즘이다.

알람 시간도 버튼 클릭을 통해 가능했는데

따로 라이브러리를 사용하지 않고 구현하느라 클릭 이벤트 관련해서는 LinearLayout 확장함수 안에서 LinearLayout과 TextView를 설정하여 구현했다.
```kotlin
private fun LinearLayout.setColor(position: Int) {
    alarmLinearLayoutList.forEach {
        if (it == this) {
            it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
            selectedTime = position
        } else {
            it.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }

    alarmTextViewList.forEachIndexed { index, textView ->
        if (index == position) {
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        } else {
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
    }
}
```

알람 시간은 초 단위로 해서 저장했다.
```kotlin
private fun calTime(): Int {
    timeString = requireContext().getString(alarmTimeList[selectedTime])
    return when (selectedTime) {
        0 -> 0
        1 -> 5
        2 -> 300
        3 -> 600
        4 -> 1800
        else -> 0
    }
}
```

모든 입력 정보를 합쳐 만든 저장 함수를 호출했다.
```kotlin
ContactStorage.addContact(
    Contact.Person(
        name = etAddContactName.text.toString(),
        phoneNumber = etAddContactPhoneNumber.text.toString(),
        department = department,
        email = etAddContactEmail.text.toString(),
        memo = etAddContactMemo.text.toString(),
        profileImage = imageUri.toString(),
        isLiked = false,
        alarm = timeString
    )
)
```

저장소에 데이터가 추가되었음을 리사이클러뷰가 있는 연락처 리스트 프래그먼트에도 알려줘야 했다.

그걸 위한 인터페이스를 만들었다.
```kotlin
interface AddContactDialogOkClick {
    fun onClick(name: String, second: Int)
}
```

이 인터페이스는 연락처 리스트 프래그먼트에서 상속받아 구현하고 리사이클러뷰에게 데이터가 변경되었다고 알려준다.
```kotlin
dialog.okClick = object: AddContactDialogOkClick {
    override fun onClick(name: String, second: Int) {
        adapter.notifyDataSetChanged()
        setAlarm(name, second)
    }
}
```

## 4. 시간 지정 알람 구현

AndroidManifest.xml에 먼저 알림 권한 설정 추가
```
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

알람에 대한 리시버를 추가했다.

추가하지 않으려고 했지만 몇 분, 몇 초 이후에 불리는 알람을 만들기 위해서는 알람 매니저와 리시버가 필요했다.

여기서는 인텐트로 이름값을 받고
```kotlin
override fun onReceive(context: Context, intent: Intent) {
    val name = intent.getStringExtra(IntentKeys.EXTRA_NAME)
    createNotification(context, name ?: "")
}
```

Notification 채널 생성 및 Notification 생성을 진행했다.
```kotlin
private fun createNotification(context: Context, name: String) {
    val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    val builder: NotificationCompat.Builder
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // 26 버전 이상
        val channelId= context.getString(R.string.notification_channel_id)
        val channelName= context.getString(R.string.notification_channel_name)
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            // 채널에 다양한 정보 설정
            description = context.getString(R.string.notification_channel_description)
            setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            setSound(uri, audioAttributes)
            enableVibration(true)
        }
        // 채널을 NotificationManager에 등록
        manager.createNotificationChannel(channel)

        // 채널을 이용하여 builder 생성
        builder = NotificationCompat.Builder(context, channelId)
    }else {
        // 26 버전 이하
        builder = NotificationCompat.Builder(context)
    }

    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    // 알림의 기본 정보
    builder.run {
        setSmallIcon(R.mipmap.ic_launcher)
        setWhen(System.currentTimeMillis())
        setContentTitle(context.getString(R.string.alarm_notification_title))
        setContentText(context.getString(R.string.alarm_notification_content, name))
        addAction(R.mipmap.ic_launcher,
            context.getString(R.string.notification_action_text), pendingIntent)
        setAutoCancel(true)
    }

    manager.notify(ConstValues.NOTIFICATION_ID, builder.build())
}
```

pendingIntent가 있는데 이를 통해 액션 버튼을 눌렀을 때 앱이 시작하도록 했다.

이 리시버를 부르는 곳은 알람 매니저 구현 함수 쪽이다.

여기서는 받아온 시간을 기준으로 알람을 세팅하고 알람이 만들어졌음을 알려주는 토스트 메세지를 띄워준다.
```kotlin
private fun setAlarm(name: String, second: Int) {
    if (second == 0) return

    val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(requireContext(), AlarmReceiver::class.java).apply {
        putExtra(IntentKeys.EXTRA_NAME, name)
    }
    val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.SECOND, second)
    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    Toast.makeText(requireContext(),
        getString(R.string.toast_message_make_notification, name), Toast.LENGTH_SHORT).show()
}
```

최종적으로 이 함수는 플로팅 버튼을 눌러 연락처 추가 다이얼로그를 가서 입력을 다하고 저장을 눌렀을 때 실행된다.

## 5. 갤러리 사진 선택 기능

내 정보 수정 다이얼로그 주요 기능 3번의 내용과 매우 흡사하다.

photo picker를 사용했고 이미지뷰에 띄웠다.

물론 약간의 변화는 저장소에 저장하기 위해 Uri를 String 타입으로 변환해 저장했다.

- 🚨 **트러블슈팅**

1. 스피너를 만들 때 좀 에로사항이 있었다.

다이얼로그 프래그먼트에서 구현하려다보니까 컨텍스트를 받아와지지 않더라

그래서 방법을 찾다보니 액티비티에서 프래그먼트로 연결되어있을 때 컨텍스트를 가져와 쓸 수 있는 requireContext()를 알게 되어 사용했다.

2. 처음 pendingIntent를 사용할 때 에러가 발생

에러 문구 : Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified when creating a PendingIntent.

왜 났는지 알아보니까 Android 12(S버전, API 레벨 31이상)에서 PendingIntent를 생성할 때 FLAG_IMMUTABLE 또는 FLAG_MUTABLE 중 하나를 지정해야 하는 요구사항을 충족하지 못한 것이었다.

PendingIntent는 앱이 다른 구성요소(예: 알림, 앱 위젯 등)에게 실행할 수 있는 작업을 전달하는 데 사용되는 객체이며 나중에 발생할 작업을 나타낸다.

해결방법은 간단하게 FLAG_IMMUTABLE 또는 FLAG_MUTABLE 중 하나를 명시적으로 지정하는 것이었다. 나는 권장하는 FLAG_IMMUTABLE을 지정했다.

3. 이미지를 에뮬레이터에 저장했는데 갤러리에 뜨지 않음

열심히 서칭한 결과 SD카드 완전 깊숙한 곳에 숨겨져 있었음

이걸 에뮬레이터 상에서 이동시켜 갤러리로 옮기면 보이고 사용이 가능하다.

4. 갤러리에서 선택한 이미지와 기존 더미 데이터로 쓰던 Drawable 리소스 이미지의 호환 문제

갤러리 선택 이미지는 URI 타입이고 Drawable 리소스 이미지는 Int형이었다.

아무리 찾고 찾아봐도 이에 대한 호환성을 가지는 함수는 찾을 수 없었다.

그래서 데이터 클래스에서 프로필 이미지를 받는 변수의 타입을 Int에서 String으로 바꾸고 두 형태의 이미지 모두 String으로 저장했다.

이를 이후에 리사이클러뷰나 상세페이지에서 이미지뷰에 띄울 때 기존 Drawable 리소스 아이디와 구별할 수 있게 하기 위해 함수를 추가로 구현했다.
```kotlin
fun checkStartAlphabet(str: String): Boolean {
    return str[0].isLetter()
}
```

두 이미지를 String으로 저장했을 때의 차이점을 보니 갤러리 이미지는 알파벳으로 시작하고 Drawable 리소스는 숫자로 시작해서 이렇게 구현했다.

그래서 이걸 사용할 때는 다음과 같이 사용했다.
```kotlin
if (ContactStorage.checkStartAlphabet(item.profileImage)) {
    ivContactListPersonProfile.setImageURI(item.profileImage.toUri())
} else {
    ivContactListPersonProfile.setImageResource(item.profileImage.toInt())
}
```

<br/>

# 내 정보 수정 다이얼로그 주요 기능
## 1. 입력값으로 마이 페이지 UI 업데이트

여기서의 흐름은 메인 액티비티의 상단 버튼을 눌러 내 정보 수정 다이얼로그에 간 다음, 정보를 입력하고 확인을 눌렀을 때 뷰페이저 안에 있는 마이 페이지 프래그먼트의 데이터를 업데이트되는 것이다.

그걸 위해 먼저 메인 액티비티에서 구현할 인터페이스를 만들었다.
```kotlin
interface OkClick {
    fun onClick(profileImage: Drawable, name: String, phoneNumber: String, email: String)
}
```

내 정보 수정 다이얼로그에서 입력한 값들을 다 파라미터로 보낸다.

이 인터페이스를 메인 액티비티에서는 다이얼로그를 초기화하고 띄울 때 구현했다.
```kotlin
editMyPageDialog.okClick = object: OkClick {
    override fun onClick(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
        adapter.editInfo(profileImage, name, phoneNumber, email)
    }
}
```

받은 값들을 다 어댑터로 보내는 걸 볼 수 있다.

내 정보 수정 다이얼로그에서 마이 페이지 프래그먼트까지 가기위해 중간다리로 뷰페이저 어댑터를 사용했다.

뷰페이저 어댑터에서 이 값을 받고 또 마이 페이지 프래그먼트의 함수로 넘겨서 최종적으로 UI를 업데이트 했다.
```kotlin
fun updateData(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
    with(binding) {
        ivMyPageProfileImage.setImageDrawable(profileImage)
        tvMyPageName.text = name
        tvMyPagePhoneNumber.text = phoneNumber
        tvMyPageEmail.text = email
    }
}
```

## 2. 마이 페이지 UI 값을 내 정보 수정 다이얼로그로 보내기

1번에서 했던 과정을 역으로 진행했다.

과정은 다음과 같다. 

메인 액티비티 -> 뷰페이저 어댑터 -> 마이 페이지 프래그먼트 -> 뷰페이저 어댑터 -> 메인 액티비티 -> 내 정보 수정 프래그먼트

메인 액티비티에서 내 정보 수정 버튼을 눌렀을 때 뷰페이저 어댑터의 함수가 호출된다.
```kotlin
ivMainMenu.setOnClickListener {
    val userInfo = adapter.getInfo()
    val userProfileImage = adapter.getImageInfo()
    ...
}
```

그러면 마이 페이지 프래그먼트에서 마이 페이지 프래그먼트의 함수를 호출한다.
```kotlin
fun getInfo(): List<String> {
    return myPageFragment.giveData()
}

fun getImageInfo(): Drawable {
    return myPageFragment.giveImageData()
}
```

마이 페이지 프래그먼트의 함수는 원하는 정보를 보내주고 이걸 받은 액티비티는 내 정보 수정 다이얼로그를 생성할 때 이 정보를 넣는다.
```kotlin
ivMainMenu.setOnClickListener {
    val userInfo = adapter.getInfo()
    val userProfileImage = adapter.getImageInfo()
    val editMyPageDialog = EditMyProfileDialog(userInfo, userProfileImage)
    editMyPageDialog.okClick = object: OkClick {
        override fun onClick(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
            adapter.editInfo(profileImage, name, phoneNumber, email)
        }
    }
    editMyPageDialog.show(
        supportFragmentManager, "EditMyProfileDialog"
    )
}
```

## 3. 갤러리 사진 선택 기능

라이브러리 추가
```
implementation("androidx.activity:activity-ktx:1.8.2") 
```

Android13부터 새롭게 등장한 Visual Media Picking Tool인 Photo Picker를 사용했다.

단일 이미지 선택이 필요했기에 PickVisualMedia 클래스를 활용해서 구현했다.

갤러리에 갔다가 돌아왔을 때 사진을 가져와서 가공해야되기 때문에, registerForActivityResult를 활용해서 콜백형식으로 구현했다.
받아오는 값은 URI 타입이기 때문에 이미지 뷰에 넣을 때도 URI 그대로 넣었다.
```kotlin
private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
    if (uri != null) {
        binding.ivEditProfileProfile.setImageURI(uri)
        binding.ivEditProfileProfile.scaleType = ImageView.ScaleType.CENTER_CROP
        selectedProfile = uri
    }
}
```

이미지만 가져오기 위해 pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) 작성했다.
```kotlin
ivEditProfileProfile.setOnClickListener {
    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}
```

마이 페이지로 이미지를 넘길 때는 Drawable 타입으로 넘겨주었다.
```kotlin
btnEditProfileCheck.setOnClickListener {
    okClick?.onClick(
        ivEditProfileProfile.drawable,
        etEditProfileName.text.toString(),
        etEditProfilePhoneNumber.text.toString(),
        etEditProfileEmail.text.toString())
    dismiss()
}
```

- 🚨 **트러블슈팅**
 
1. 내 정보 수정 다이얼로그에서 값을 저장하고 리사이클러뷰에 알려주는 것에 어려움이 있었다.

생각해보니 그냥 액티비티에서 할 수 있는게 아니었다.

다이얼로그 프래그먼트에서 액티비티를 거쳐 뷰 페이저 안에 있는 마이 페이지 프래그먼트에 접근해야 리사이클러뷰 데이터 업데이트가 가능했다.

그래서 정말 어댑터 형태를 바꿔보기도 하고, 어댑터 프래그먼트 추가를 따로 해보기도 하고, supportFragmentManager.findFragmentByTag도 써보고, Bundle도 사용했지만 실패

제일 가깝게 한 건 adapter를 새로 만들어 프래그먼트를 새로 세팅하고 마이페이지를 첫 페이지로 세팅하는 거였지만 애니메이션 효과가 발생해서 실패

이외에 ViewPager에 프래그먼트가 배정되어있지 않다고 하거나 프래그먼트가 null이라는 에러가 떴다.

이후 조언을 구했다.

어댑터에서 프래그먼트를 따로 선언 및 초기화하는 것이 해답이 되었고 어댑터는 액티비티가 종료될 때까지 살아있고 그렇다면 만든 프래그먼트도 계속 살아있있다.

이를 통해 프래그먼트의 함수를 만들어 접근이 가능해지고 역으로도 가능했다.


# 마이 페이지 주요 기능
## 1. 정보 유지

sharedPreference를 이용해 앱이 꺼졌다가 다시 켜져도 마이 페이지에 정보가 저장되도록 했다.

먼저 저장공간을 선언한다.
```kotlin
requireContext().getSharedPreferences("sp", MODE_PRIVATE)
```

그리고 저장 함수와 불러오기 함수를 만들었다.
```kotlin
private fun saveUserProfile(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
    val stream = ByteArrayOutputStream()
    profileImage.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream) // Drawable을 Bitmap으로 변환
    val byteArray = stream.toByteArray()
    val editor = sharedPreferences.edit()
    editor.putString(IntentKeys.EXTRA_STORE_PROFILE_IMAGE, Base64.encodeToString(byteArray, Base64.DEFAULT)) // Bitmap을 Base64 문자열로 인코딩
    editor.putString(IntentKeys.EXTRA_STORE_NAME, name)
    editor.putString(IntentKeys.EXTRA_STORE_PHONE_NUMBER, phoneNumber)
    editor.putString(IntentKeys.EXTRA_STORE_EMAIL, email)
    editor.apply()
}

private fun loadUserProfile() {
    val storedProfileImage = sharedPreferences.getString(IntentKeys.EXTRA_STORE_PROFILE_IMAGE, "")
    if (storedProfileImage != "") {
        val byteArray = Base64.decode(storedProfileImage, Base64.DEFAULT) // Base64 문자열을 Bitmap으로 디코딩
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        with(binding) {
            ivMyPageProfileImage.setImageBitmap(bitmap) // Bitmap을 이용해 이미지 띄우기
            tvMyPageName.text = sharedPreferences.getString(IntentKeys.EXTRA_STORE_NAME, "")
            tvMyPagePhoneNumber.text = sharedPreferences.getString(IntentKeys.EXTRA_STORE_PHONE_NUMBER, "")
            tvMyPageEmail.text = sharedPreferences.getString(IntentKeys.EXTRA_STORE_EMAIL, "")
        }
    }
}
```

저장 함수는 내 정보 수정 다이얼로그에서 값이 넘어와 UI가 업데이트 될 때 실행되고 불러오기 함수는 onViewCreated에서 실행된다.

-  🚨 **트러블슈팅**

1. sharedPreference에 저장하고 불러올 때의 이미지 처리

그냥 String으로 바꾸고 Int로 바꾸는 식으로는 되지 않더라(받아오는 타입이 Drawable이어서 그랬던 거 같다.)

이를 위해 방법을 찾아보니까 Bitmap을 들렀다가 Base64 문자열 찍고 다시 역순으로 오는 방법이 있었다.

위의 코드에서 그 부분이 잘 해결되어 나와있다.

2. 내 정보 수정 다이얼로그에서 키보드가 올라올 때 마이 페이지의 레이아웃이 올라오는 현상

처음에는 TextView가 사라졌다고 생각했는데 가장 아래에 있던 탭레이아웃이 올라오는 거였다.

이를 위해 매니페스트 파일의 windowSoftInputMode 설정을 해주었다.

windowSoftInputMode를 이용하면 키보드가 나타나고 사라졌을 때의 화면 변화 방식을 지정해 줄 수 있다.

기본값은 adjustUnspecified이고 나는 키보드가 올라왔을 때 화면의 크기가 변하지 않기를 원하므로 adjustPan을 사용했다.
```
<activity
    android:name=".mainActivity.MainActivity"
    android:exported="true"
    android:windowSoftInputMode="adjustPan">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

