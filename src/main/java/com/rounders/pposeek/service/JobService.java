package com.rounders.pposeek.service;

import com.rounders.pposeek.dto.JobDto;
import com.rounders.pposeek.dto.CompanyInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    
    private List<JobDto> mockJobs = Arrays.asList(
        JobDto.builder()
            .id(1L)
            .title("프론트엔드 개발자")
            .company("네이버")
            .location("서울 강남구")
            .salary("4000-6000만원")
            .employmentType("정규직")
            .experience("3년 이상")
            .education("대학교 졸업")
            .tags(Arrays.asList("React", "TypeScript", "Next.js"))
            .postedDate(LocalDate.now().minusDays(7))
            .deadline(LocalDate.now().plusDays(30))
            .matchScore(95)
            .description("네이버에서 프론트엔드 개발자를 모집합니다.")
            .requirements(Arrays.asList("React 3년 이상", "TypeScript 경험"))
            .benefits(Arrays.asList("4대보험", "연봉 협상 가능"))
            .companyInfo(CompanyInfoDto.builder()
                .name("네이버")
                .size("1000명 이상")
                .industry("IT/인터넷")
                .description("대한민국 대표 IT 기업")
                .logo("/naver-logo.png")
                .build())
            .build(),
        JobDto.builder()
            .id(2L)
            .title("백엔드 개발자")
            .company("카카오")
            .location("경기 성남시")
            .salary("5000-7000만원")
            .employmentType("정규직")
            .experience("5년 이상")
            .education("대학교 졸업")
            .tags(Arrays.asList("Java", "Spring", "Kafka"))
            .postedDate(LocalDate.now().minusDays(3))
            .deadline(LocalDate.now().plusDays(25))
            .matchScore(88)
            .description("카카오에서 백엔드 개발자를 모집합니다.")
            .requirements(Arrays.asList("Java 5년 이상", "MSA 경험"))
            .benefits(Arrays.asList("4대보험", "스톡옵션"))
            .companyInfo(CompanyInfoDto.builder()
                .name("카카오")
                .size("1000명 이상")
                .industry("IT/인터넷")
                .description("대한민국 대표 IT 기업")
                .logo("/kakao-logo.png")
                .build())
            .build()
    );
    
    public Page<JobDto> getJobs(String search, List<String> tags, String location, 
                                String employmentType, Pageable pageable) {
        List<JobDto> filtered = mockJobs.stream()
            .filter(job -> search == null || job.getTitle().contains(search) || 
                          job.getCompany().contains(search))
            .filter(job -> tags == null || tags.isEmpty() || 
                          job.getTags().stream().anyMatch(tags::contains))
            .filter(job -> location == null || job.getLocation().contains(location))
            .filter(job -> employmentType == null || job.getEmploymentType().equals(employmentType))
            .collect(Collectors.toList());
            
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filtered.size());
        
        return new PageImpl<>(filtered.subList(start, end), pageable, filtered.size());
    }
    
    public JobDto getJobById(Long id) {
        return mockJobs.stream()
            .filter(job -> job.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Job not found"));
    }
    
    public List<JobDto> getRecommendedJobs(String token) {
        return mockJobs.stream()
            .sorted((a, b) -> b.getMatchScore().compareTo(a.getMatchScore()))
            .limit(5)
            .collect(Collectors.toList());
    }
    
    public void applyToJob(Long jobId, String token) {
        // 지원 로직 구현
    }
    
    public void bookmarkJob(Long jobId, String token) {
        // 북마크 로직 구현
    }
}
