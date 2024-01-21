# HTTP 
- 2024.01.16 `9주차`
- Serialization(JSON)
- @RequestMapping 옵션
- @RequestHeader & HttpHeaders
- @ResponseBody
- @RequestBody

`@RequestMapping` 어노테이션에 대한 옵션 속성들과`@RequestHeader`의 옵션과 사용방법, `HttpHeaders` 사용방법  
그리고 `@ResponseBody` 어노테이션의 역할, `@RequestBody` 어노테이션의 역할 등에 대해 학습한 프로젝트다.

## 스택

- Spring Boot 3.2.1
- Spring Web
- Spring Boot Data JPA
- H2 Database
- SQLite

## Key Point

[RequestMapping](/src/main/java/com/example/http/MappingController.java)
```java
  // @RequestMapping에 전달된 파라미터를 바탕으로
  // 어떤 HTTP 요청에 대해서 메서드가 실행되는지
  @RequestMapping(
    value = "/example/{pathVar}",
    // method: 어떤 HTTP 메서드에 대해 실행이 되는지
    method = { RequestMethod.GET, RequestMethod.POST },
    // consumes: 어떤 데이터 형식에 대해 실행이 되는지
    // 요청의 Content-Type 헤더
    consumes = MediaType.APPLICATION_JSON_VALUE,
    // headers: 어떤 헤더가 포함되어야 실행이 되는지
    headers = "x-likelion=hello",
    // params: 어떤 Query Parameter가 있어야 하는지("이 파라미터가 있어야 해당 메서드를 실행하겠다"란 의미)
    params = "likelion=hello"
  )
```
[@RequestHeader](/src/main/java/com/example/http/MappingController.java)
`특정 헤더 하나만 가져오고 싶을 때`
```java
  // 특정 헤더 하나를 가져오고 싶을 때
  @PostMapping("/single-header")
  @ResponseBody
  public String singleHeader(
    // @RequestHeader
    // : HTTP 요청에 포함된 Header 하나 가져오고 싶을 때 @RequestHeader 어노테이션을 활용할 수 있다.
    // 해당 헤더가 요청에 포함되어 있지 않으면 실행할 수 없다란 응답이 돌아온다.
    // (보안에 활용할 수 있다.)
    @RequestHeader("content-Type") String contentType
  ) {
    return contentType; // Headers, Key: Content-Type  Value: application/json
  }

@PostMapping("/option-header")
@ResponseBody
public String optionHeader(
@RequestHeader(
  value = "x-likelion",
  // required: 포함을 반드시 해야하는지, 만약 값이 없다면 null값이 들어오고 오류가 생기지 않는다.
  required = false
) String likelionHeader,
@RequestHeader(
  value = "x-likelion-default",
  required = false,
  // defaultValue: 포함 안되었을 때 기본값 설정
  defaultValue = "hello"
) String likelionDefaultHeader
  ) {
  log.info(likelionHeader);
  log.info(likelionDefaultHeader);
  return likelionHeader;
  }
```
[@HttpHeaders](/src/main/java/com/example/http/HeaderController.java)
`특정 헤더가 아닌 전체 헤더를 보고 싶을 때`
```java
  // 특정 헤더가 아닌 전체 헤더를 보고 싶을 때
  @PostMapping("/all-header")
  @ResponseBody
  public String allHeaders(
    @RequestHeader
    // HttpHeaders, Map<String, String>를 사용하면 전체 헤더를 다 확인할 수 있다.
    HttpHeaders headers
  ) {
    for (Map.Entry<String, List<String>> entry: headers.entrySet()) {
      log.info(String.format(
        "%s: %s", entry.getKey(), entry.getValue()
      ));
    }
    return "done";
  }
```

[@ResponseBody & @RequestBody](/src/main/java/com/example/http/ArticleController.java)



## 복습
~~2024.01.21 완료~~
