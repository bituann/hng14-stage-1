package com.bituan.hng14_stage_1.repository;

import com.bituan.hng14_stage_1.model.ApiResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApiRepository extends JpaRepository<ApiResult, UUID> {
    Optional<ApiResult> findByName (String name);
    boolean existsByName (String name);
}
