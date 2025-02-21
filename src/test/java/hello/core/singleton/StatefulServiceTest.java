package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void StatefulServiceTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : A사용자 주문금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}