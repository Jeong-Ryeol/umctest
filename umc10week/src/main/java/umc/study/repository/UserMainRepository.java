package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.UserMain;

public interface UserMainRepository extends JpaRepository<UserMain, Long> {
}
