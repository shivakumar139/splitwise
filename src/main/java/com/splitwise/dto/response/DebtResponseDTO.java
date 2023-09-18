package com.splitwise.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DebtResponseDTO {
    private UserResponseDto payer;
    private UserResponseDto payee;
    private Double amount;
}
