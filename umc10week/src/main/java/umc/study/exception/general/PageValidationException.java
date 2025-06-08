package umc.study.exception.general;

import umc.study.ApiPayload.code.BaseErrorCode;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.exception.GeneralException;


public class PageValidationException extends GeneralException {

    public PageValidationException() {
        super(ErrorStatus.INVALID_PAGE_NUMBER);
    }
}
