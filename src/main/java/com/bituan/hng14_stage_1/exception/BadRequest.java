package com.bituan.hng14_stage_1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRequest extends RuntimeException {
    private String message;
}
