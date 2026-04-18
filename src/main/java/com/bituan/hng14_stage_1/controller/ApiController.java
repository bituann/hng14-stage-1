package com.bituan.hng14_stage_1.controller;

import com.bituan.hng14_stage_1.enums.AgeGroup;
import com.bituan.hng14_stage_1.exception.BadRequest;
import com.bituan.hng14_stage_1.exception.NotFound;
import com.bituan.hng14_stage_1.model.*;
import com.bituan.hng14_stage_1.repository.ApiRepository;
import com.bituan.hng14_stage_1.service.AgifyApiService;
import com.bituan.hng14_stage_1.service.GenderizeApiService;
import com.bituan.hng14_stage_1.service.NationalizeApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiController {
    final AgifyApiService agifyApiService;
    final GenderizeApiService genderizeApiService;
    final NationalizeApiService nationalizeApiService;

    final ApiRepository apiRepository;

    @PostMapping("/profiles")
    public ResponseEntity<ApiResponse> classifyName (@RequestBody ProfilesPostApiRequest request) {
        String name = request.getName();

        if (name == null || name.isBlank()) {
            throw new BadRequest("Missing or empty name");
        }

        if (apiRepository.existsByName(name)) {
            ApiResult result = apiRepository.findByName(name).orElseThrow(() -> new NotFound("Profile not found"));

            ApiResponse response = new ApiResponse("success", "Profile already exists", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        GenderizeApiResponse genderizeApiResponse = genderizeApiService.classifyName(name);
        AgifyApiResponse agifyApiResponse = agifyApiService.classifyName(name);
        NationalizeApiResponse nationalizeApiResponse = nationalizeApiService.classifyName(name);

        int age = agifyApiResponse.getAge();

        ApiResult result = ApiResult.builder()
                .name(name)
                .gender(genderizeApiResponse.getGender())
                .genderProbability(genderizeApiResponse.getProbability())
                .sampleSize(genderizeApiResponse.getCount())
                .age(age)
                .ageGroup(AgeGroup.resolve(age))
                .countryId(nationalizeApiResponse.getHighestProbabilityCountryId())
                .countryProbability(nationalizeApiResponse.getHighestProbability())
                .createdAt(ZonedDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS))
                .build();

        apiRepository.save(result);

        ApiResponse response = new ApiResponse("success", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public ResponseEntity<AllProfilesApiResponse> getProfiles (@RequestParam(required = false) String gender, @RequestParam(required = false) String countryId, @RequestParam(required = false) String ageGroup) {
        List<ApiResult> profileList = apiRepository.findAll();

        if (gender != null && !gender.isBlank()) {
            profileList = profileList.stream().filter((a) -> a.getGender().equalsIgnoreCase(gender)).toList();
        }

        if (countryId != null && !countryId.isBlank()) {
            profileList = profileList.stream().filter((a) -> a.getCountryId().equalsIgnoreCase(countryId)).toList();
        }

        if (ageGroup != null && !ageGroup.isBlank()) {
            profileList = profileList.stream().filter((a) -> a.getAgeGroup() == AgeGroup.valueOf(ageGroup.toLowerCase())).toList();
        }

        AllProfilesApiResponse response = new AllProfilesApiResponse("success", profileList.size(), profileList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<ApiResponse> getUser (@PathVariable UUID id) {
        ApiResult result = apiRepository
                .findById(id)
                .orElseThrow(() -> new NotFound("Profile not found"));

        ApiResponse response = new ApiResponse("success", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable UUID id) {
        apiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
