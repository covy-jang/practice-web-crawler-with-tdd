# webCrawlerWithTDD

1. 개요
   - url 별로 가장 많이 검색된 단어를 Json 형태로 보여줄 수 있는 RestfulAPI 서버
   - 프레임워크 / 라이브러리 : Spring Boot 2.4.3 / Apache Maven / Jsoup

2. 상세기능
   - http://www.naver.com 를 기준으로 단어 파싱 및 저장
   - 하이퍼 링크를 다시 순환하여 단어 파싱 및 저장
   - 특수문자 / 영문자를 제거 후 한글만 저장
   - url 재탐색의 경우 100번을 초과하면 Stop
   - url 별 제일 많이 등장한 5개 단어를 Json 형태로 반환
   
3. TDD 내용
   - 네트워크 단절 시 url의 html 접근이 불가능하기 때문에, mocking 데이터가 로컬 html을 반환하여 검증하는 테스트
   - Url 호출 시 서비스(url별 가장많이 호출된 단어 5개)를 mocking 하여 반환된 데이터를 검증하는 테스트 ( 일반 mocking, BDD 2개)
   - Client를 Mocking하여 데이터를 정상적으로 가져오는 지와 http://www.naver.com / "구독" 이라는 단어가 포함되어 있는지 검증하는 테스트
