package com.bituan.hng14_stage_1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnprocessableEntity extends RuntimeException {
    private String message;
}
