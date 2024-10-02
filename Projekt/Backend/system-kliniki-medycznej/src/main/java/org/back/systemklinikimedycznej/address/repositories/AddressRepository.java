package org.back.systemklinikimedycznej.address.repositories;

import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
