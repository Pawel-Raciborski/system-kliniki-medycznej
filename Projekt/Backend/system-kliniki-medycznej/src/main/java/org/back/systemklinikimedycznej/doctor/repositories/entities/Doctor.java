package org.back.systemklinikimedycznej.doctor.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.doctor.repositories.entities.calendar.DoctorCalendar;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;

import java.time.LocalDate;
import java.util.Set;

@With
@Setter
@Getter
@Entity
@Builder
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private DoctorCalendar calendar;

    @Column(name = "description", length = 2056)
    private String description;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<DoctorSpecialization> doctorSpecializations;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private Set<DoctorOfficeHours> doctorOfficeHours;

    @Column(name = "profile_image_path", length = 1024)
    private String profileImagePath;

    @Column(name = "pwz_number")
    private String pwzNumber;

    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private Set<Prescription> prescriptions;
}
