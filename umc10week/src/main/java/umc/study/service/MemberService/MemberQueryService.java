package umc.study.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.ApiMission1.code.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
