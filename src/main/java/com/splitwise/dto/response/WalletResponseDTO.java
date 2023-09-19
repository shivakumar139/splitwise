package com.splitwise.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDTO {
    private UserResponseDto user;
    private Double payable;
    private Double own;
    private Double amount;
}
