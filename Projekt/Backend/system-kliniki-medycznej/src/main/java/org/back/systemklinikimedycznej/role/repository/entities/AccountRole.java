package org.back.systemklinikimedycznej.role.repository.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

@Getter
@Setter
@Builder
@Entity
@Table(name = "account_role")
@NoArgsConstructor
@AllArgsConstructor
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
