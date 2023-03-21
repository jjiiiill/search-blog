# 블로그 검색 서비스
- Kakao Blog 검색 Open API 및 Naver Blog 검색 Open API를 이용한 블로그 검색 서비스 구현
  - Kakao Blog 검색 API 호출 실패시, Naver Blog 검색 Open API 호출

# 개발환경
- Springboot 3.0.4
- Kotlin 1.7.22 (Java 17)
- Gradle 7.6.1
- Webflux
- Spring JPA
- H2

# 로컬 실행 방법
### Gradle Boot Run
- Gradle Build를 통해 Dependencies Download
- Gradle Boot Run (port: 8080)
### Gradle BootJar
- Gradle BootJar를 통해 jar 파일 생성({localProjectRootDir}/build/libs/serachengine-0.0.1-SNAPSHOT.jar)
- jar 파일 실행
  - $ java -jar {localProjectRootDir}/build/libs/serachengine-0.0.1-SNAPSHOT.jar
### jar 파일 다운로드
- github project root dir내 "search-blog.jar" 파일 다운로드 (https://github.com/jjiiiill/search-blog/blob/master/search-blog.jar)
- java -jar search-blog.jar

# API
### 1. 블로그 검색 API
- [GET] /blogs
    - Test: http://localhost:8080/blogs?query=테스트&sort=ACCURACY&page=1&size=10
    - Query parameters:
  ```json
      "query": "test", // String, 검색어
      "sort": "ACCURACY", // String, 정렬 조건 (ACCURACY: 정확도순(default), RECENCY: 최신순)
      "page": 1, // Int, 조회 페이지 (1~50, default: 1)
      "size": 10, // Int, 한 페이지에 보여질 문서 (1~50, default: 10)
  ```
    - Response Body: 200, OK
    ```json
    {
      "meta": {
        "totalCount": 11047378, // Int, 검색된 문서 수
        "pageableCount": 800, // Int, total_count 중 노출 가능 문서 수
        "isEnd": false,  // Boolean, 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
      },
      "documents": [
        {
          "title": "<b>테스트</b>", // String, 블로그 글 제목
          "contents": "<b>테스트</b>", // String, 블로그 글 요약
          "url": "http://test.com", // String, 블로그 글 URL
          "datetime": "2023-03-16 13:25:51" // datetime, 블로그 글 작성시간 (yyyy-MM-dd HH:mm:ss)
        },
        ...
      ]
    }
    ```
    - Exception:
        - 올바르지 않은 요청: 400, Bad Request

### 2. 인기 검색어 목록 조회 API
- [GET] /blogs/top-searched-words
    - Test: http://localhost:8080/blogs/top-searched-words
    - Query parameters:
    ```json
        "start": "2023-03-22 00:00:00", // datetime, 조회 시작 시간 (yyyy-MM-dd HH:mm:ss, default: now - 1hour)
        "end": "2023-03-22 01:00:00" // datetime, 조회 종료 시간 (yyyy-MM-dd HH:mm:ss, default: now)
    ```
    - Response Body: 200, OK
    ```json
    {
      "topSearchedWords": [
        {
          "word": "카카오", // String, 검색 단어
          "count": 3 // Int, 검색 횟수
        },
        {
          "word": "네이버",
          "count": 2
        },
        ...
      ]
    }
    ```
    - Exception:
        - 올바르지 않은 요청: 400, Bad Request
