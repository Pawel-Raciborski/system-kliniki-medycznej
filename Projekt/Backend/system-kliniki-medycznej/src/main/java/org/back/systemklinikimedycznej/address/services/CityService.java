package org.back.systemklinikimedycznej.address.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.exceptions.CityNotFoundException;
import org.back.systemklinikimedycznej.address.repositories.CityRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.City;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public City findByName(String cityName){
        return cityRepository.findByName(cityName).orElseThrow(() -> new CityNotFoundException("City with name [%s] not found", HttpStatus.NOT_FOUND));
    }
}
