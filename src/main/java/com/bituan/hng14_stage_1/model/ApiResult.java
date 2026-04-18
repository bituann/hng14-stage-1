package com.bituan.hng14_stage_1.model;

import com.bituan.hng14_stage_1.enums.AgeGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class ApiResult {
    @Id
    @GeneratedValue
    @UuidGenerator(algorithm = org.hibernate.id.uuid.UuidVersion7Strategy.class)
    private UUID id;
    private String name;
    private String gender;
    private double genderProbability;
    private int sampleSize;
    private int age;
    private AgeGroup ageGroup;
    private String countryId;
    private double countryProbability;
    private ZonedDateTime createdAt;
}
