package umc.study.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.study.ApiMission1.code.MemberResponseDTO;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.ApiMission1.code.MemberRequestDTO;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.exception.handler.FoodCategoryHandler;
import umc.study.exception.handler.MemberHandler;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberRepository;
import umc.study.ApiMission1.code.MemberRequestDTO;
import umc.study.converter.MemberConverter;
import umc.study.ApiMission1.code.MemberResponseDTO;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;



    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        // 비밀번호 암호화
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        // 선호 카테고리 ID로 FoodCategory 엔티티 조회
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(categoryId ->
                        foodCategoryRepository.findById(categoryId)
                                .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND))
                ).collect(Collectors.toList());

        // MemberPrefer 매핑 객체 생성
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 각각의 MemberPrefer에 Member 객체 설정
        memberPreferList.forEach(mp -> mp.setMember(newMember));

        // Member에 리스트 연결 (양방향 매핑일 경우 필요)
        newMember.setMemberPreferList(memberPreferList);

        //**<`PasswordEncoder`를 사용한 비밀번호 암호화 사용 이유>**
        //
        //1. 보안 강화: 평문 비밀번호를 데이터베이스에 저장하지 않아 보안을 강화합니다.
        //2. 단방향 해시: `BCryptPasswordEncoder`는 단방향 해시 함수를 사용하여 원본 비밀번호를 복원할 수 없게 만듭니다.
        //3. 솔트(Salt) 사용: BCrypt는 자동으로 솔트를 생성하여 레인보우 테이블 공격을 방지합니다.


        return memberRepository.save(newMember);
    }
    @Override
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return MemberConverter.toLoginResultDTO(
                member.getId(),
                accessToken
        );
    }

}
