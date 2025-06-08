package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.study.domain.Member;
import umc.study.domain.enums.MemberStatus;

import java.util.List;
import java.util.Optional;

//public interface MemberRepository extends JpaRepository<Member, Long> {
//    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.status = :status")
//    List<Member> findByNameAndStatus(String name, MemberStatus status);
//
//}
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email); //특정 이메일을 사용하는 유저가 DB에 존재하는지 확인하기 위한 함수를 추가
}