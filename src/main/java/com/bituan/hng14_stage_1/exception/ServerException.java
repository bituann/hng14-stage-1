package com.bituan.hng14_stage_1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServerException extends RuntimeException {
    private String message;
}
