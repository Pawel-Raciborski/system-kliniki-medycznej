package org.back.systemklinikimedycznej.role.repository;

import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);
}
