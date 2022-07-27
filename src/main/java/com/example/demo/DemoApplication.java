package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 어노테이션은 해당 클래스가 스프링 부트를 설정하는 클래스임을 의미한다.
// 또 스프링은 이 어노테이션이 달린 클래스가 있는 패키지를 베이스 패키지로 간주한다.
// 스프링은 베이스 패키지와 그 하위 패키지에서 자바 빈을 찾아 스프링의 의존성 주입 컨테이너 오브젝트, 즉 ApplicationContext에 등록한다.
// 그리고 어플리케이션 실행 중 어떤 오브젝트가 필요한 경우 의존하는 다른 오브젝트를 찾아 연결해준다.
// @Component 어노테이션은 스프링에게 이 클래스를 자바 빈으로 등록시키라고 알려주는 어노테이션이다.

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
