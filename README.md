# webCrawlerWithTDD

1. Restful API 를 하기 기능 구현
   - 웹엔진 : http://www.naver.com 를 기준으로 단어 파싱 및 저장
   - 특수문자 / 영문자를 제거 후 한글만 저장
   - 하이퍼링크 파싱 후 재탐색하는 로직 적용
   - url 재탐색의 경우 100번을 초과하면 Stop
   - url 별 제일 많이 등장한 5개 단어를 Json 형태로 반환
   
2. TDD 내용
   - WebCrawlService를 Mocking 하여 HtmlBody를 얻는부분을 Stub 처리
   - TextContentProviderController를 Mocking하여 데이터를 정상적으로 반환하는지 확인
   - 실제 RestfulAPI를 호출하는 대상(Client)을 Mocking하여 1번에 해당하는 내용을
       정상적으로 반환하는지 체크
