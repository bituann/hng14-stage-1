package com.bituan.hng14_stage_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GenderizeApiResponse {
    private String gender;
    private float probability;
    private int count;
}
