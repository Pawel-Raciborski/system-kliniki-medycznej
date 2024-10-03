package org.back.systemklinikimedycznej.personal_details;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.personal_details.mapper.PersonalDetailsMapper;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personal-details")
@RequiredArgsConstructor
public class PersonalDetailsController {
    private final PersonalDetailsService personalDetailsService;

    @PutMapping("/update")
    public ResponseEntity<PersonalDetailsDto> update(@RequestBody PersonalDetailsDto personalDetails) {
        PersonalDetailsDto updatedPersonalDetails = PersonalDetailsMapper.INSTANCE.mapFromEntity(
                personalDetailsService.update(personalDetails)
        );
        return ResponseEntity.ok(updatedPersonalDetails);
    }
}
