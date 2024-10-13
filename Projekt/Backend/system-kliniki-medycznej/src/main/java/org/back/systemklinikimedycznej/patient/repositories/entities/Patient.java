package org.back.systemklinikimedycznej.patient.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;
    @Column(name = "parent_pesel")
    private String parentPesel;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;

    @OneToOne(mappedBy = "patient")
    private PatientCard patientCard;
}
