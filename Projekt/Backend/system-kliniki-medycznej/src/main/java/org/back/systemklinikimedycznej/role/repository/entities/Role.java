package org.back.systemklinikimedycznej.role.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<RolePermission> rolePermissions;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<AccountRole> accountRoles;
}
