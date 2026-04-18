package com.bituan.hng14_stage_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ApiResponse {
    private final String status;
    private String message;
    private final ApiResult data;
}
