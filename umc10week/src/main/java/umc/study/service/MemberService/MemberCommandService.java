package umc.study.service.MemberService;

import umc.study.ApiMission1.code.MemberRequestDTO;
import umc.study.ApiMission1.code.MemberResponseDTO;
import umc.study.domain.Member;


public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
