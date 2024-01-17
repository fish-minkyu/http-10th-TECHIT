package com.example.http;

import com.example.http.dto.ResponseDto;
import com.example.http.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class StudentController {

  @PostMapping("/student")
  @ResponseBody
  public StudentDto newStudent(
    @RequestBody StudentDto dto
  ) {
    log.info(dto.toString());
    dto.setAge(dto.getAge() +1);
    return dto;
  }

  /*
  StudentDto 형태의 JSON 데이터를 받아서
  {
    "message": "등록 완료"
  }
  라는 JSON을 반환하도록
   */
  @PutMapping("/student")
  @ResponseBody
  public ResponseDto putStudent(
    @RequestBody StudentDto studentDto
  ) {
   ResponseDto responseDto = new ResponseDto();
   responseDto.setMessage("등록 완료");
   return responseDto;
  }

  @PostMapping("/student/entity")
  public ResponseEntity<ResponseDto> postEntity(
    @RequestBody StudentDto studentDto
  ) {
    log.info(studentDto.toString());
    ResponseDto responseDto = new ResponseDto();
    responseDto.setMessage("등록 완료");
    HttpHeaders headers = new HttpHeaders();
    headers.add("x-likelion-response", "OK");
    headers.add("x-likelion-sequence", "1");

//    return new ResponseEntity<>(
//      responseDto, HttpStatus.CREATED
//      );

    return new ResponseEntity<>(
      responseDto, headers, HttpStatus.ACCEPTED
    );
  }

  // GetMapping으로 만들었네? 그래도 Body를 첨부할 수 있다.
  @GetMapping("/student/bad-request")
  public ResponseEntity<ResponseDto> badRequest(
    @RequestBody StudentDto studentDto
  ) {
    ResponseDto responseDto = new ResponseDto();
    if (studentDto.getAge() < 10) {
      // responseDto의 message에 "너무 어려요"
      // 응답코드 400으로 응답(Bad Request)
      responseDto.setMessage("너무 어려요");
      return new ResponseEntity<>(
        responseDto, HttpStatus.BAD_REQUEST
      );
    } else {
      // message에 "등록 완료"
      // 응답코드 200으로 응답
      responseDto.setMessage("등록 완료");
//      return new ResponseEntity<>(
//        responseDto, HttpStatus.OK
//      );

      // new 키워드 없이 보내는 방법
      return ResponseEntity.ok(responseDto);
    }
  }
}
