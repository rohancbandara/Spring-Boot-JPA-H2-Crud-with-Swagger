package com.rcb.dto;


import com.rcb.constants.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private String id;
    private String fullName;
    private String age;
    private CommonStatus status;
}
