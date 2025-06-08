package umc.study.service.MemberService;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import umc.study.ApiMission1.code.MemberResponseDTO;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.exception.handler.MemberHandler;
import umc.study.repository.MemberRepository;

@EnableTransactionManagement
@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    //private final MemberConverter memberConverter;

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request){
        Authentication authentication = JwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member);
    }
}
