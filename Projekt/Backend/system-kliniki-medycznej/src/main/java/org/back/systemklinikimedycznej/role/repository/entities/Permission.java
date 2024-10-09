package org.back.systemklinikimedycznej.role.repository.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "permission")
    private Set<RolePermission> rolePermissions;
}
