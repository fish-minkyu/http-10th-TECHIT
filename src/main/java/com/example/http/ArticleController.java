package com.example.http;

import com.example.http.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@Controller
// 모든 하위 메서드에 @ResponseBody를 추가하는 어노테이션
@RestController
public class ArticleController {
  // 사용자가 /articles/create로
  // 데이터를 첨부해 요청을 보낼 때,
  // 그 데이터를 ArticleDto의 형태로 받는 것
  @PostMapping("/articles/create")
  // @ResponseBody
  // 메서드의 반환값을 View로 취급하지 않고
  // 순수한 전달될 HTTP Response Body로 취급하는 어노테이션이다.
//  @ResponseBody
  public ArticleDto create(
    // RequestBody는 역직렬화가 가능하면 Spring Boot가 붙여준다.
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
