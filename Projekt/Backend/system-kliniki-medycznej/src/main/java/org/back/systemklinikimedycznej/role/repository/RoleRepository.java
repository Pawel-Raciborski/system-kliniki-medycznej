package org.back.systemklinikimedycznej.role.repository;

import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Query("""
    SELECT r FROM Role as r
    WHERE r.name NOT IN :roleNames
    """)
    List<Role> findRolesNotIn(@Param("roleNames") List<String> roleNames);
}
