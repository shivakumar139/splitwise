package com.splitwise.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotEmpty(message = "Email is missing")
    private String email;

    @NotEmpty(message = "Password is missing")
    private String password;
}
