package com.bituan.hng14_stage_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AllProfilesApiResponse {
    private String status;
    private int count;
    private List<ApiResult> data;
}
