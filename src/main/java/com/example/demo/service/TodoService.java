package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// 어떤 로그는 정보를 위한 것이고, 어떤 로그는 디버깅을 위한 자세한 정보일 수 있으며, 또 일부 로그는 심각한 에러를 알려주는 로그일 수도 있다.
// 이렇게 용도에 따라 로그를 크게 info, debug, warn, error 로 나누고 이를 로그 레벨이라고 부른다.
// 우리에겐 이미 이런 기능을 제공하는 slf4j라는 라이브러리가 존재한다.
// 로깅은 웹 서비스에서 반드시 필요하다 !
// slf4j는 로그계의 JPA 정도로 생각하면 된다. slf4j를 사용하려면 구현부를 연결해줘야한다.
// 이 연결 작업은 스프링이 알아서 해준다.
// 스프링은 기본적으로 Logback 로그 라이브러리를 사용한다.
@Slf4j // 로그 라이브러리를 사용하려면 로깅할 대상 클래스 위에 다음과 같은 어노테이션을 추가하면 된다.
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

    public List<TodoEntity> create(final TodoEntity entity){
        // 1. 검증
        // 넘어온 엔티티가 유효한지 검사하는 로직으로 이 부분은 코드가 더 커지면 TodoValidator.java로 분리시킬 수 있다.
        validate(entity);
        // 2. save()
        // 엔티티를 데이터베이스에 저장하고 로그를 남긴다.
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());

        // 3. findByUserId()
        // 저장된 엔티티를 포함하는 새 리스트를 리턴한다.
        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity){
        // 1. 저장할 엔티티가 유효한지 확인
        validate(entity);

        // 2. 넘겨받은 엔티티 Id 를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트를 할 수 없기 때문이다.
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {

            // 3. 반환된 TodoEntity 가 존재하면 값을 새 엔티티 값으로 덮어 씌운다.
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // 4. 데이터베이스에 새 값을 저장한다.
            repository.save(todo);
        });

        // 사용자의 모든 Todo 리스트를 리턴한다.
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }

    private void validate(TodoEntity entity) {
        if(entity == null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null");
        }


        if(entity.getUserId() == null){
            log.warn("Unknown User.");
            throw new RuntimeException("Unknown User.");
        }
    }
}
