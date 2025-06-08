package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserMainIdAndStoreId(Long userMainId, Long storeId);
    //워크북9주차
    Page<Review> findAllByStore(Store store, Pageable pageable); // 가게 기준 리뷰 조회

    Page<Review> findAllByMember(Member member, Pageable pageable); // 회원 기준 리뷰 조회
}