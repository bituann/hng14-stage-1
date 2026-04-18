package com.bituan.hng14_stage_1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BadGateway extends RuntimeException {
    private String message;
}
