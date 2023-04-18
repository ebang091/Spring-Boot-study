package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach //매 태스트에서 할 메소드
    public void afterEach(){
        repository.clearStore();
    }

    @Test//Junit 단위로 테스트할 수 있다. 단 순서 보장이 안되므로 데이터 클리어를 위해 afterEach가 있다.
    public void save()
    {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//get으로 꺼내는게 좋은 방법은 아님. 암튼 Optional에서 꺼내기 위해 get tkdyd

        assertThat(member).isEqualTo(result);//둘이 같은 지 확인한다.
        // System.out.PrintIn("result = " = + (result == member));
        // Assertions.asertEquals(member, result); //(result, expeceted) (순서는 확실하지 않음)
        // Assertions(org....).assertThat(member).isEqualTo(result);
        // import 한 후 assertThat(member).isEqaulTo(result);
        // 테스트 성공 시에는 초록 불이 뜬다.
        //실무에서는 빌드 단계에서 테스트가 통과하지 않으면 다음 단계로 넘어가지 못하게 만듬.



    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
