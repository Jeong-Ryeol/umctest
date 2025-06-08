package umc.study.exception.handler;

import umc.study.ApiPayload.code.BaseErrorCode;

public class MemberHandler extends RuntimeException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode.toString());
    }
}

