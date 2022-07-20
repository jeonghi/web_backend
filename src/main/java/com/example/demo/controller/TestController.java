package com.example.demo.controller;


import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// GET http://localhost:8080/test
// RestController 를 이용하면 Http 와 관련된 코드 및 요청/ 응답 매핑을 스프링이 알아서 해준다.
@RestController // 이 컨트롤러가 RestController 임을 명시함.
@RequestMapping("test") // URI 경로에 매핑
public class TestController {

    @GetMapping// HTTP 메소드에 매핑
    public String testController() {
        return "Hello World!";
    }

    @GetMapping("/testGetMapping") // GET http://localhost:8080/test/testGetMapping
    public String testControllerWithPath(){
        return "Hello World! testGetMapping ";
    }

    @GetMapping("/{id}") // GET http://localhost:8080/test/{id}
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequestParam") // GET http://localhost:8080/test/testRequestParam?id={id}
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        return "Hello World! ID " + id;
    }

    // object 를 request body 로 받은경우
    @GetMapping("/testRequestBody") // GET http://localhost:8080/test/testRequestBody {"id" : {id}, "message" : {massage}}
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        // 보내오는 JSON 형태의 String 을 TestRequestBodyDTO 객체로 변환 후 변수에 전달한다.
        return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage() ;
    }

    // object 를 response body 로 보낼경우
    @GetMapping("/testResponseBody") // GET http://localhost:8080/test/testResponseBody
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm Response DTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity") // GET http://localhost:8080/test/testResponseEntity
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        // http status 를 400으로 설정
        return ResponseEntity.ok().body(response);
    }
}
