package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    //레포지토리에 저장해두고 afterEach문을 사용하기 위해 MemoryMemberRepository!를 할당.
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//    //MemberService 내에서 보면 MemoryMemberRepository를 또 할당하는데
//    //일단 static변수라 문제는 없지만 문제가 생길 수 있음! (게다가 왜 다른 객체를? 테스트내와 memberService 내에서)

    //같은 인스턴스를 쓰게 바꾸자.
    //실제 MemberSerivce 내의 변수에서 정의만 해두고, 생성자를 따로 정의한다. (인자로 받은 애를 저장)
    //이를 Dependency Injection , DI라고 한다.
    //  public MemberService(MemberRepository memberRepository){
    //        this.memberRepository = memberRepository;
    //    }

    //외부에서 넣어주게.
    MemoryMemberRepository memberRepository;
    @BeforeEach

    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService  = new MemberService(memberRepository);

    }


    @AfterEach //매 태스트에서 할 메소드
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //레포지토리에 회원가입 join 함수가 잘 구현되어있는 지 확인하는 테스트 코드 .
        //멤버를 동적할당하고 이름을 설정한 후, 회원가입을 시키고, 레포지토리를 이용해서 다시 꺼내온 후
        //assertThat().isEqaulTo()를 이용해서 맞게 가져왔는지 확인한다.

        //중복회원을 가입시킬 땐 예외가 터지는 지도 확인해야 한다.


        //테스트코드는 한글로 적을 수도 있음 : 함수이름.
        //given -> when (이렇게 하면 = 검증대상) -> then (결과가 어떻게 나와야 하는지. )
        //given

        Member member = new Member();
        member.setName("hello");
        //when

        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //given
        memberService.join(member1);
        //assertThrows라는 함수!
        //오른쪽 매개변수를 실행하면 왼쪽 매개변수 에러가 터져야한다! 라는 뜻  memberService.join(member1);
        //assertThrows(IllegalStateException.class, ()->memberService.join(member2));

        //출력도 확인해보려면
        //command  + option + v 로 값을 받아와서
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //try- catch 문을 이용한 오류확인하기.

        //when
//
//        try {
//            memberService.join(member2);
//            fail();//지나가면 fail이라는 뜻.
//        }catch(IllegalStateException e)
//        {
//        //이게 정상이라는 뜻.
//            //더 확인하기
        //then
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//        }



    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}