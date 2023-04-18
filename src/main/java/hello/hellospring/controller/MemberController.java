package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
//Controller라는 어노테이션을 붙이면, spring이 처음에
//spring container라는 spring 빈 통이 생기는데
//@Controller 가 붙은 클래스의 객체를 생성해서 통에 넣어둔다.
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

}

