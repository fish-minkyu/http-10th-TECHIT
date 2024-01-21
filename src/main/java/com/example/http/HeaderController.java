package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

// header를 받을 수 있는 방법이 1가지 방법만 있는 것은 아니다.
@Slf4j
@Controller
public class HeaderController {
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
}
