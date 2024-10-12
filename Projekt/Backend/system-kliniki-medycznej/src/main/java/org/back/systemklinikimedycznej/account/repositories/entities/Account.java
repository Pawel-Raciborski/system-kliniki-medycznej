package org.back.systemklinikimedycznej.account.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String email;

    @Column(name = "date_time_of_creation")
    private LocalDateTime dateTimeOfCreation;

    @OneToMany(mappedBy = "account")
    private Set<AccountRole> accountRoles;
}
