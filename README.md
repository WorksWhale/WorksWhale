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

## 🚨 트러블슈팅

