package com.bituan.hng14_stage_1.enums;

public enum AgeGroup {
    child, teenager, adult, senior;

    public static AgeGroup resolve (int age) {
        if (age >= 60) return senior;
        if (age >= 20) return adult;
        if (age >= 13) return teenager;
        return child;
    };
}
