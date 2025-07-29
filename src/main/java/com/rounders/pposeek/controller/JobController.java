package com.rounders.pposeek.controller;

import com.rounders.pposeek.dto.JobDto;
import com.rounders.pposeek.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    
    private final JobService jobService;
    
    @GetMapping
    public ResponseEntity<Page<JobDto>> getJobs(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String employmentType,
            Pageable pageable) {
        return ResponseEntity.ok(jobService.getJobs(search, tags, location, employmentType, pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    
    @GetMapping("/recommended")
    public ResponseEntity<List<JobDto>> getRecommendedJobs(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(jobService.getRecommendedJobs(token));
    }
    
    @PostMapping("/{id}/apply")
    public ResponseEntity<Void> applyToJob(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        jobService.applyToJob(id, token);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/bookmark")
    public ResponseEntity<Void> bookmarkJob(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        jobService.bookmarkJob(id, token);
        return ResponseEntity.ok().build();
    }
}
