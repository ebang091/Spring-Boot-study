package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {


    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    회원가입
    리포지토리에 save

     */
    public Long join(Member member)
    {

        validateDuplicateMember(member);
        //메소드로 바꿔버리기-> ctrl + t -> Extract method (refactor 와 관련한 메소드들이 나온다.)
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원은 허용 x

        //널을 바로 넣지 않고, 널을 Optional로 감싸주기 때문에,
        // ifPresent메소드를 이용할 수 있다. (권장하지는 않으나 get()으로 바로 꺼낼수는 있다. result.get())

        //(orElseGet 같은 메소드도 사용가능.)
        //1.
//        Optional<Member> result = memberRepository.findByName((member.getName()));
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //Optional 반환 처리는 안이쁘기 때문에 바로 ifPresent와 합쳐서.
        memberRepository.findByName(member.getName())
                          .ifPresent(m -> {
                              throw new IllegalStateException("이미 존재하는 회원입니다.");
                          });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        //서비스는 비즈니스 관련한 용어로, 레포지토리는 개발스럽게 용어를 선택한다.
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
