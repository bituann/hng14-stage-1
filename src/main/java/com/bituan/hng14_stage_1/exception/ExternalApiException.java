package com.bituan.hng14_stage_1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExternalApiException extends RuntimeException {
    private int code;
    private String message;
}
