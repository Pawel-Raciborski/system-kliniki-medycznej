package org.back.systemklinikimedycznej.role.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@Table(name = "permission")
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.REMOVE)
    private Set<RolePermission> rolePermissions;
}
