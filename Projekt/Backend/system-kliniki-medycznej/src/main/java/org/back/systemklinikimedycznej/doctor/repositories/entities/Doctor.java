package org.back.systemklinikimedycznej.doctor.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.doctor.repositories.entities.calendar.DoctorCalendar;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.user.repositories.entities.Account;

import java.time.LocalDate;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;

    @OneToOne
    @JoinColumn(name="calendar_id")
    private DoctorCalendar calendar;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.PERSIST)
    private Set<DoctorSpecialization> doctorSpecializations;

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorOfficeHours> doctorOfficeHours;

    @Column(name = "pwz_number")
    private String pwzNumber;

    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions;
}
