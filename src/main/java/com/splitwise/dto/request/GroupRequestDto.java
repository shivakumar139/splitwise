package com.splitwise.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequestDto {

    @NotEmpty(message = "Name is missing")
    private String name;

    @NotNull(message = "Created By ID is missing")
    private UUID createdBy;

    private String description;
}
