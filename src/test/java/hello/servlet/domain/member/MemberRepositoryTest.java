package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("Jbiscode", 29);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findById() {
//given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        Member findMember = memberRepository.findById(member1.getId());
        //then
        assertThat(findMember).isEqualTo(member1);

    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("A", 20);
        Member member2 = new Member("B", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).contains(member1, member2);
    }

    @Test
    void clearStore() {
    }
}