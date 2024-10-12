package org.back.systemklinikimedycznej.role.repository;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRoleRepository extends JpaRepository<AccountRole,Long> {

    @Query("""
    SELECT DISTINCT r FROM AccountRole ar
    JOIN ar.role r
    JOIN ar.account acc
    WHERE acc.username = :username
    """)
    List<Role> findAllAccountRoles(@Param("username") String username);

    @Query("""
    SELECT count(acc) FROM AccountRole accRole
    JOIN accRole.account acc
    JOIN accRole.role r
    WHERE r.name = :roleName
    """)
    Long countAccountsWithRole(@Param("roleName") String roleName);

    Optional<AccountRole> findByAccountAndRole(Account account, Role role);
}
