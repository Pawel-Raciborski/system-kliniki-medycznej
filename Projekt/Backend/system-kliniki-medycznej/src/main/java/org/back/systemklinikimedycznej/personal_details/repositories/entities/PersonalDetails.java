package org.back.systemklinikimedycznej.personal_details.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@Table(name = "personal_details")
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pesel;
    private String name;
    private String surname;
    @Column(name="birth_date")
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    private String gender;
    @Column(name="phone_number")
    private String phoneNumber;
}

