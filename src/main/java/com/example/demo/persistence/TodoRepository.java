package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository 는 인터페이스이다.
// 이 인터페이스를 사용하려면 새 인터페이스를 작성해 JpaRepository를 확장해야한다.
// 이 때 JpaRepository<T, ID> 가 제너릭타입을 받는것에 주의해야한다.
// T는 테이블에 매핑될 엔티티 클래스이고,
// ID는 이 엔티티의 Primary키의 타입이다.
// TodoRepository는 인터페이스인데 어떻게 TodoRepository를 구현하는 클래스가 없이도 동작하는 것일까?
// 우리는 추상클래스나 인터페이스는 반드시 구현하는 클래스가 있어야 사용할 수 있다는 법칙을 알고 있다.
// 하지만 적어도 JpaRepository에서는 그 법칙을 무시하는 것 같다.
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    @Query("select * from Todo t where t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);
}
