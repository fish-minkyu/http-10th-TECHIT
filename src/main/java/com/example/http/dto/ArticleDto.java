package com.example.http.dto;

import lombok.Data;

// ArticleDto
// : 사용자가 작성하고 싶은 게시글 데이터를 의미한다.

// 만약에 ArticleDto를 JSON 형태로 표현하고 싶다면?
// 제목: 직렬화 역직렬화
// 내용: Serialization

/*
  JSON으로 표현된 데이터(역직렬화: JSON -> Java 객체)
  {
    "title": "직렬화 역직렬화",
    "content"; "Serialization"
  }

  Java에서 실제로 사용하는 객체(직렬화: Java객체 -> JSON)
  ArticleDto dto = new ArticleDto();
  dto.setTitle("직렬화 역직렬화");
  dto.setcontent("Serialization")
*/
@Data
public class ArticleDto {
  private String title;
  private String content;
}
