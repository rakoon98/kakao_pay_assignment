## kakao_pay_assignment ##
카카오페이 사전과제
    
## 기술스택 ##
- Kotlin (필수)
- MvvM
- Coroutines
- LiveData
- Navigation
- Retrofit & OkHttp
- Glide
- LifeCycle
- Dagger-hilt
- RecyclerView & DiffUtil

## 구현 조건 ##
- 페이지 당 50개씩 나오게 개발
- 스크롤시 다음 페이지 나오게 구현
- 리스트결과 아이템 클릭시 상세 화면으로 이동
- 리스트화면,상세화면 모두 Fragment 구현 

## 느낀점 ##
조금 욕심을 내서 써보지 않았거나, 연습으로만 써본 것들을 포함해서 구현해보았습니다.

특히나 Di 로 Koin 을 썼었는데, 구글 공식발표로 나온 Dagger-hilt 가 눈에 밟혀 공부하면서 적용해본게 
Dagger 를 안드로이드 적용하기 좋게, 최적화하여 내놓았다고 하여 (비록 아직 alpha 이지만) 
스스로 생각하기에도 좀 인상적이었습니다. 

DiffUtil 을 이번에 처음 적용을 해보았는데, 알려진대로 정말 너무 편하고 성능 최적화 된다는 느낌이 
어느정도 와 닿아서 좋았습니다.

Navigation argument 를 이용한 내용 전달도 처음 써보았는데, Navigation 을 한층 더 알게 되는 경험이었습니다.

카카오 사전과제라는 생각에 좀 더 트렌디하게 최신 기술을 적용하지 못하고 있는 부분을
채워나가면서 구현해보려고 좀 참고를 많이하면서 구현하였는데, 어떤지 이후에도 계속 공부를 해야겠다고 
생각되었고, 역시 개발에서 새로운 기술에 대한 공부는 중요하고 재밌다고 느낄 수 있는 시간이었습니다.

## 참고 ##
Dagger-hilt 참고 Git : https://github.com/iambaljeet/JetPackHiltDemo
Dagger-hilt Google Docs : https://developer.android.com/training/dependency-injection/hilt-android?hl=ko

Navigation Google Docs : https://developer.android.com/guide/navigation/navigation-pass-data

DiffUtil 참고 Blog : https://blog.kmshack.kr/RecyclerView-DiffUtil%EB%A1%9C-%EC%84%B1%EB%8A%A5-%ED%96%A5%EC%83%81%ED%95%98%EA%B8%B0/
DiffUtil Google Docs : https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil