package com.security.springsecurity_6.persistens.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter

@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String name;
}
