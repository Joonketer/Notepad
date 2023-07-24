package com.backend.global.error.exception;

import com.backend.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
