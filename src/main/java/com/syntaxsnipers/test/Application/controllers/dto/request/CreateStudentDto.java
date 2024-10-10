package com.syntaxsnipers.test.Application.controllers.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CreateStudentDto {
    private String name;
    private int age;
    private String gender;
    private String address;
}
