package com.splitwise.splitter;

import com.splitwise.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwedDetails {
    private User user;
    private double share;

}
