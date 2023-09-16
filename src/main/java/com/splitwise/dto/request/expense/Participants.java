package com.splitwise.dto.request.expense;

import com.splitwise.enums.ParticipantType;
import com.splitwise.validator.annotations.NotBlankEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participants {

    @NotBlankEnum(enumClass = ParticipantType.class ,message = "Invalid Participant Type")
    @Enumerated(EnumType.STRING)
    private ParticipantType type;

    private List<Double> shares;

    @NotEmpty(message = "ids are missing")
    private List<String> ids;

}
