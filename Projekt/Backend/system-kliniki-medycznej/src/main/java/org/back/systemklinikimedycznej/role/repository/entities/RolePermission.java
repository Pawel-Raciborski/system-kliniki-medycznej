package org.back.systemklinikimedycznej.role.repository.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
