package com.rounders.pposeek.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoDto {
    private String name;
    private String size;
    private String industry;
    private String description;
    private String logo;
}
