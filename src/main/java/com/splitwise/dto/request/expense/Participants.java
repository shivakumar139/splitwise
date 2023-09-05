package com.splitwise.dto.request.expense;

import com.splitwise.enums.ParticipantType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participants {

    @Enumerated(EnumType.STRING)
    private ParticipantType type;

    private Map<UUID, Double> userIdsWithShare;

    private Map<UUID, Double> groupIdsWithShare;

    private Set<UUID> userIds;


}
