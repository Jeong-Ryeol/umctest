package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.ApiMission1.code.StoreResponseDTO;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public StoreResponseDTO.ReviewPreViewListDTO getMyReviewList(Long memberId, int page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        PageRequest pageRequest = PageRequest.of(page, 10);  // 한 페이지 10개
        Page<Review> reviewPage = reviewRepository.findAllByMember(member, pageRequest);

        return StoreConverter.toReviewPreViewListDTO(reviewPage);
    }
}
