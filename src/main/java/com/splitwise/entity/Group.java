package com.splitwise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Name is missing")
    private String name;

    @NotEmpty(message = "Creator User id is missing")
    @ManyToOne
    @JoinColumn(name = "fk_creator_id")
    private User createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String description;

    @ManyToMany
    @JoinTable(
            name = "group_member",
            joinColumns = @JoinColumn(name = "fk_group_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_user_id")
    )
    private List<User> users;






}