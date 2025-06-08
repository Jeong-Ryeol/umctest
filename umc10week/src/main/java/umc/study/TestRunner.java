package umc.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.enums.SocialType;
import umc.study.repository.MemberRepository;


import java.util.List;

@Component
public class TestRunner implements CommandLineRunner {

    private final MemberRepository memberRepository;

    public TestRunner(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {


//        List<Member> members = memberRepository.findByNameAndStatus("베뉴", MemberStatus.ACTIVE);
//        for (Member member : members) {
//            System.out.println(member.getName() + " : " + member.getStatus());
//        }
    }
}
