package io.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close();
    }

    /**
     * 프로토 타입 빈 특징 :
     * 스프링 컨테이너에 요청할 때 마다 새로 생성
     * 스프링 커테이너는 프로토타입 빈 생성과 의존관계 주입 & 초기화 까지 관여
     * 종료 메서드가 호출 x
     * 프로토 타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리, 종료 메서드에 대한 호출도 클라이언트가 직접 해야 함
     */
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
