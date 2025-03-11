package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDiAspect.class)
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK 동적 프록시, DI 예외 발생
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB, 성공
public class ProxyDITest {

    @Autowired MemberService memberService;         // JDK 동적 프록시 O, CGLIB O
    @Autowired MemberServiceImpl memberServiceImpl; // JDK 동적 프록시 X, CGLIB O

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
