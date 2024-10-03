package org.back.systemklinikimedycznej.address.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.exceptions.CityNotFoundException;
import org.back.systemklinikimedycznej.address.repositories.CityRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.City;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public City findByName(String cityName){
        Optional<City> cityOpt = cityRepository.findByName(cityName);
        return cityOpt.orElseGet(() -> cityRepository.save(City.builder().name(cityName).build()));
    }
}
