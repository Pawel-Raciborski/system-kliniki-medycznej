package org.back.systemklinikimedycznej.receptionist.repositories.entities;

import jakarta.persistence.*;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.user.repositories.entities.User;

import java.time.LocalDate;

@Entity
@Table(name="receptionist")
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;
    @Column(name="date_of_employment")
    private LocalDate dateOfEmployment;
}
