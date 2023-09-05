package com.splitwise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

}

