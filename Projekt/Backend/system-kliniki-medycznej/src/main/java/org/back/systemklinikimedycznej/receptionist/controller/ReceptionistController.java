package org.back.systemklinikimedycznej.receptionist.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistDto;
import org.back.systemklinikimedycznej.receptionist.controller.dto.RegisterReceptionistForm;
import org.back.systemklinikimedycznej.receptionist.mapper.ReceptionistMapper;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.back.systemklinikimedycznej.receptionist.services.ReceptionistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receptionist")
@RequiredArgsConstructor
public class ReceptionistController {
    private final ReceptionistService receptionistService;
    @PostMapping("/create")
    public ResponseEntity<ReceptionistDto> create(
            @RequestBody RegisterReceptionistForm registerReceptionistForm
            ){
        ReceptionistDto createdReceptionist = ReceptionistMapper.INSTANCE.mapFromEntity(receptionistService.register(registerReceptionistForm));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReceptionist);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestParam(name="email") String email
    ){
        receptionistService.delete(email);

        return ResponseEntity.ok().build();
    }
}
