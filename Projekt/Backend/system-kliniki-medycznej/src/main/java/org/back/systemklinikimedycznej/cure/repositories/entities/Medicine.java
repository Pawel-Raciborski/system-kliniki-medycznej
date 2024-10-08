package org.back.systemklinikimedycznej.cure.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDiseaseMedicine;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "cure")
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "api_medicine_id")
    private Long apiMedicineId;
    @Column(name = "specimen_type")
    private String specimenType;
    @Column(name = "medicinal_product_name")
    private String medicinalProductName;
    @Column(name = "common_name")
    private String commonName;
    @Column(name = "pharmaceutical_form_name")
    private String pharmaceuticalFormName;
    @Column(name = "medicinal_product_power")
    private String medicinalProductPower;
    @Column(name = "active_substance_name")
    private String activeSubstanceName;
    @Column(name = "subject_medicinal_product_name")
    private String subjectMedicinalProductName;
    @Column(name = "registry_number")
    private String registryNumber;
    @Column(name = "procedure_type_name")
    private String procedureTypeName;
    @Column(name = "expiration_date_string")
    private String expirationDateString;
    @Column(name = "atc_code")
    private String atcCode;
    @Column(name = "target_species")
    private String targetSpecies;

    @OneToMany(mappedBy = "medicine")
    private Set<PatientDiseaseMedicine> patientDiseaseMedicines;
}
