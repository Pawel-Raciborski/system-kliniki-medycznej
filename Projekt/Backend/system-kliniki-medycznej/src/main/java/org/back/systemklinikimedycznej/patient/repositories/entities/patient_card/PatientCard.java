package org.back.systemklinikimedycznej.patient.repositories.entities.patient_card;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "patient_card")
@NoArgsConstructor
@AllArgsConstructor
public class PatientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name="patient_id")
    private Patient patient;
    @Column(name="created_date_time")
    private LocalDateTime createdDateTime;
    @Column(name="last_visit_date_time")
    private LocalDateTime lastVisitDateTime;
    @OneToOne
    @JoinColumn(name="interview_id")
    private Interview interview;
    @Column(name="patient_data_files_path")
    private String patientDataFilesPath;
    @OneToOne
    @JoinColumn(name="patient_details_id")
    private PatientDetails patientDetails;

    @OneToMany(mappedBy = "patientCard")
    private Set<PatientDisease> patientDiseases;

    @OneToMany(mappedBy = "patientCard")
    private Set<Appointment> appointments;

    public void removePersonalDetailsId() {
        this.setPatientDetails(null);
    }
}
