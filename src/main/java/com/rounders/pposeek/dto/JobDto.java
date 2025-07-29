package com.rounders.pposeek.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private Long id;
    private String title;
    private String company;
    private String location;
    private String salary;
    private String employmentType;
    private String experience;
    private String education;
    private List<String> tags;
    private LocalDate postedDate;
    private LocalDate deadline;
    private Integer matchScore;
    private String description;
    private List<String> requirements;
    private List<String> benefits;
    private CompanyInfoDto companyInfo;
}
