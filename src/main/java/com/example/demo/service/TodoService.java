package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;
    public String testService() {
        // Todo 엔티티 생성
        TodoEntity entity = TodoEntity.builder().title("첫 번째 투투 아이템 생성").build();
        // Todo 엔티티 저장
        repository.save(entity);
        // Todo 엔티티 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get(); // findById의 반환값이 Optional<T>임 . 옵셔널에는 종단 처리로 get 이 존재함.

        return savedEntity.getTitle();
    }
}
