package com.splitwise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.01")
    private Double payable;

    @DecimalMin(value = "0.00", message = "Amount must be greater than or equal to 0.01")
    private Double own;

    @NotNull(message = "Amount is missing")
    private Double amount;

    @PrePersist
    void setAmountBeforeSaving(){
        double amt = (own - payable);
        amt = Math.round(amt * 100.00)/100.0;
        this.amount = amt;


        payable = Math.round(payable * 100.00)/100.0;
        own = Math.round(own * 100.00)/100.0;
    }

}