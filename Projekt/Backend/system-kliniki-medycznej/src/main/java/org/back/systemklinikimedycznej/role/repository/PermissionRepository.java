package org.back.systemklinikimedycznej.role.repository;

import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);

    @Query(
            """
            SELECT p FROM Permission p
            WHERE p.name NOT IN :permissionNames
            """
    )
    List<Permission> findAllNotInPermissionNames(@Param("permissionNames") List<String> permissionNames);
}
