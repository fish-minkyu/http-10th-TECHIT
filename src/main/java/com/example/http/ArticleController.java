package com.example.http;

import com.example.http.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// 모든 하위 메서드에 @ResponseBody를 추가하는 어노테이션
@RestController
public class ArticleController {
  // 사용자가 /articles/create로
  // 데이터를 첨부해 요청을 보낼 때,
  // 그 데이터를 ArticleDto의 형태로 받는 것
  @PostMapping("/articles/create")
  // @ResponseBody
  // : 메서드의 반환값을 View로 취급하지 않고
  // 순수한 전달될 HTTP Response Body로 취급하는 어노테이션이다.
//  @ResponseBody
  public ArticleDto create(
    // @RequestBody는
    // : 역직렬화가 가능하면 Spring Boot가 Java 객체로 변환해준다.
    @RequestBody ArticleDto dto
  ) {
    log.info(dto.toString());
    String title = dto.getTitle();
    String content = dto.getContent();
    dto.setTitle(title);
    dto.setContent(content);
    return dto;
  }
}
// @RequestBody
// : 매개변수에 붙이는 어노테이션, Request의 Body에 있는 직렬화된 데이터를 Java객체로 바꿔주는 역직렬화를 해준다.
// => RequestBody가 붙어있는 파라미터는 Spring Boot가 내부적으로 작동한다.
// 사용자가 보내준 데이터가 직렬화된 데이터여서 역직렬화 시키
// 어떤 특정한 Java 객체로 표현할 수 있다라고 한다면 @RequestBody 붙어있는 객체에 할당해준다.
// => 메소드에 매개변수를 작성해주고 거기에 @RequestBody를 붙여주면
// 사용자가 보내준 데이터를 해당 매개변수에 알맞은 형태로 할당해줄 수 있다.
// (직렬화 -> 역직렬화 과정이 저절로 이뤄진다.)
// (HTTP 요청을 dispatcher servlet을 비롯한 Spring 요소들이 적당히 해석해서 Java객체로 변환해준다.)

// @ResponseBody
// : 메소드에 붙이는 어노테이션, 메소드의 반환값을 view가 아닌 Response Body로 취급한다.
// => 해당 어노테이션이 없으면 문자열은 View Resolver로 전달이 된다.
// 그럼 View Resolver는 templates 폴더에서 적당한 html이 있는지 찾아보고 해당 html을 잘 구성해서 사용자에게 다시 보내준다.


