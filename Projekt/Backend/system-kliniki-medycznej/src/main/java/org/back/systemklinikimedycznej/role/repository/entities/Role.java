package org.back.systemklinikimedycznej.role.repository.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.account.repositories.entities.AccountRole;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<RolePermission> rolePermissions;

    @OneToMany(mappedBy = "role")
    private Set<AccountRole> accountRoles;
}
