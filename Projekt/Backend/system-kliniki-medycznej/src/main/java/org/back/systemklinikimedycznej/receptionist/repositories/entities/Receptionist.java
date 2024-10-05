package org.back.systemklinikimedycznej.receptionist.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;

import java.time.LocalDate;

@Entity
@Table(name="receptionist")
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;
    @OneToOne
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;
    @Column(name="date_of_employment")
    private LocalDate dateOfEmployment;
}
