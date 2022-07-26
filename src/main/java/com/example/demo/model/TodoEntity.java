package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// 엔티티 클래스는 그 자체가 테이블을 정의해야한다.
// 하나의 엔티티 인스턴스는 데이터베이스 테이블의 한 행에 해당한다.
// 자바 클래스를 엔티티로 정의할 때 주의해야 할 점이 몇 가지 있다.
// 첫 번쨰, 클래스에는 매개변수가 없는 생성자, NoArgsConstructor 가 필요하다.
// 두 번째, Getter/ Setter 가 필요하다.
// 세 번째, 기본키(Primary Key)를 지정해 줘야 한다.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")// 이 어노테이션을 이용해 자바 클래스를 엔티티로 지정할 수 있다.
public class TodoEntity {
    @Id // @Id 는 기본 키가 될 필드에 지정한다.
    @GeneratedValue(generator = "system-uuid") // system-uuid 는 @GenericGenerator 에 정의된 제너레이터의 이름이다.
    @GenericGenerator(name="system-uuid", strategy = "uuid") // 하이버네이트가 제공하는 기본 제너레이터가 아닌 커스텀 제너레이터를 사용하고 싶을 때 이용한다.
    private String id; // 이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    private String title; // Todo 타이틀
    private boolean done; // todo 완료 여부
}