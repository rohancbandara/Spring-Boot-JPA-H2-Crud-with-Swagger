package com.rcb.dto;


import com.rcb.constants.CommonStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInsertDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String age;
    private CommonStatus status;
}
