package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    private String error;
    // TodoDTO뿐만 아니라 이후 다른 모델의 DTO도 ResponseDTO를 이용해 클라이언트에게 리턴할 수 있도록 제너릭을 이용함.
    private List<T> data;
}
