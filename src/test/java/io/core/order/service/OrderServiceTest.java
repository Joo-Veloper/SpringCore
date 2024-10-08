package io.core.order.service;

import io.core.global.order.service.OrderService;
import io.core.global.scan.AppConfig;
import io.core.global.member.entity.Grade;
import io.core.global.member.entity.Member;
import io.core.global.member.service.MemberService;
import io.core.global.order.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

   /* MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();*/

    @Test
    void crateOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}