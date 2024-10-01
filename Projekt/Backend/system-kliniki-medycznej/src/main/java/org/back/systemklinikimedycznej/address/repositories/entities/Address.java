package org.back.systemklinikimedycznej.address.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@Table(name="address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    @Column(name="apartment_number")
    private String apartmentNumber;

    @Column(name="postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;
}
