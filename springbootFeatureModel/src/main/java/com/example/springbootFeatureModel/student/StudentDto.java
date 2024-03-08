package com.example.springbootFeatureModel.student;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @NotBlank(message = "name: Name is empty")
    @NotNull(message = "name: Name is NULL")
    @Size(min = 3, max = 30, message = "name: Must be of 3 - 30 characters")
    private String name;

    @NotNull(message = "email: Email is null")
    @NotEmpty(message = "email: Email is empty")
    @Email(message = "email: Invalid email address")
    String email;



    @NotBlank(message = "phone: Phone is empty")
    @NotNull(message = "phone: Phone is NULL")
    String phone;






}
