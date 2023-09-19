package com.splitwise.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    @NotEmpty(message = "Name is missing")
    private String name;

    @NotEmpty(message = "Email is missing")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Mobile is missing")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobile;

    @NotEmpty(message = "Password is missing")
    @Size(min = 7, max = 30, message = "Password length should in between 7 to 30")
    private String password;
}
