package com.app.posts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permission")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 100)
    private String name;
}
