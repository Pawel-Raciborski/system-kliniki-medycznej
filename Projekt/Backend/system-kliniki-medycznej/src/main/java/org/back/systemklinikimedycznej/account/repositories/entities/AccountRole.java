package org.back.systemklinikimedycznej.account.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

@Entity
@Table(name = "account_role")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
