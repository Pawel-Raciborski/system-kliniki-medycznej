package org.back.systemklinikimedycznej.disease.repository.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "icd11code")
    private String icd11Code;
    @Column(name = "icd11title")
    private String icd11Title;
    @Column(name = "icd10code")
    private String icd10Code;
    @Column(name = "icd10title")
    private String icd10Title;
}
