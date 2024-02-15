공공 데이터 API를 사용하여 DB까지 연결하는 프로그램

업데이트 해야 할 기능 
- > 현재 한 페이지에 10개씩 받아오므로 totalCount 값을 읽어와 pageNo를 for문으로 처리 하여 한번에 모두 받아 올 수 있는 방안 
- > 검색 기능을 구현한다면 url의 keyword 값이 unicode이므로 변환 시켜주어야 하며 serviceKey를 숨길 수 있는 방안
    그 외에도 원하는 url을 직접 변경하지 않아도 자유롭게 변경하여 넣을 수 있는 방안 
- > json을 java object로 변경하는 과정에서 중첩 클래스를 사용하였는데 myBatis에서 중첩된 객체타입(현재 item)을 불러오지 못하는 문제
    그에 따라 Item 객체를 직접 사용하지 못하고 ApiDTO를 다시 생성하여 set하는 과정이 추가됨

그 외 --- XML 형식의 parse 방법, url이 아닌 외부 json file을 parse 하는 방법


1. 공공 데이터 API URL 생성

  ![활용 신청](https://github.com/leeyehji/practice/assets/152182670/c69b6b62-7577-41e0-86fe-f971d341f10b)


   공공 데이터 API를 이용 하기 위해 먼저 data.go.kr 에서 공공데이터 활용 신청을 하고 인증키를 발급받는다


  ![스크린샷 2024-02-15 232545](https://github.com/leeyehji/practice/assets/152182670/3f1829d9-a110-489d-a7f3-76c009157c81)


  ![스크린샷 2024-02-16 000820](https://github.com/leeyehji/practice/assets/152182670/bca20389-5bdf-4523-b5d6-2778091ef73d)


   활용신청 상세기능정보란에서 미리보기를 누르고 아래 parameter들을 입력하고 미리보기를 하면 URL이 생성된다.

  

   한국관광공사_TourAPI활용매뉴얼(국문)_v4.1_01 에서 get 방식(? 변수=값 & 변수=값 )을 사용해 URL을 직접 작성해도 좋다.



3. API 요청을 위한 java class 생성
   ResponseApi.java

  ![스크린샷 2024-02-15 230604](https://github.com/leeyehji/practice/assets/152182670/8794b036-a9e3-4228-9468-a16f6a9f7a87)

   Java에서 url을 활용해 Json 문자열을 받아오면 성공이다.
   
   
4. 중요) Json 문자열을 Java Object로 변환을 위한 DTO(데이터 전달 객체)를 생성한다

   현재 프로그램에서 받아온 공공데이터 Json의 경우

  ![객체생성](https://github.com/leeyehji/practice/assets/152182670/f89d050b-7a7d-4ff8-8ef8-3b0a246478b9)

   위의 Json 형식을 가지고 있으므로

   각각의 상위 객체는 하위 객체 를 포함 하고 있는 형태로 생성한다.

   노드의 마지막 타입은  " " - > String , 숫자 -> int 형

   변수명은 실제 json 문자열과 똑같이 하며 이후 DB table도 똑같은 이름으로 생성한다.

5. Json library ( jackson , Gson , JSONsimple )

  ![스크린샷 2024-02-15 235443](https://github.com/leeyehji/practice/assets/152182670/a1d23f80-452a-4ef5-8978-7cee5730e3ff)

   JSON 문자열을 Java Object로 변환 해주는 library 

  ![스크린샷 2024-02-15 235449](https://github.com/leeyehji/practice/assets/152182670/65604f02-2648-43f0-af30-d6f5bedf8bc5)

   jar 파일을 내려받아 Build-Path 해준다.

6. JsonParse.java
   
   jackson library 를 import 하고 메소드를 사용 하여 JSON 문자열을 Java Object로 변환 하여
   
   최상위 객체에서부터 getter로 값을 꺼낸다. 

7. DB 연결 -> DAO , myBatis를 사용 한다.
   
