package org.back.systemklinikimedycznej.patient.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.user.repositories.entities.Account;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;
    @Column(name = "parent_pesel")
    private String parentPesel;
    @OneToOne
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;
}
