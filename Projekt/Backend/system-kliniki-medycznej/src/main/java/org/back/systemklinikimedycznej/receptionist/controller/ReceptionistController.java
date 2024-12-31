package org.back.systemklinikimedycznej.receptionist.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistDetails;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistDto;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistInfo;
import org.back.systemklinikimedycznej.receptionist.controller.dto.RegisterReceptionistForm;
import org.back.systemklinikimedycznej.receptionist.mapper.ReceptionistMapper;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.back.systemklinikimedycznej.receptionist.services.ReceptionistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptionist")
@RequiredArgsConstructor
public class ReceptionistController {
    private final ReceptionistService receptionistService;

    @PostMapping("/create")
    public ResponseEntity<ReceptionistInfo> create(
            @RequestBody RegisterReceptionistForm registerReceptionistForm
    ) {
        ReceptionistInfo createdReceptionist = ReceptionistMapper.INSTANCE.mapFromEntityToReceptionistInfo(receptionistService.register(registerReceptionistForm));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReceptionist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceptionistDetails> getReceptionist(@PathVariable("id") Long receptionistId) {
        ReceptionistDetails receptionistDetails = ReceptionistMapper.INSTANCE.mapFromEntityToReceptionistDetails(
                receptionistService.findById(receptionistId)
        );

        return ResponseEntity.ok(receptionistDetails);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ReceptionistInfo> delete(
            @PathVariable("id") Long id
    ) {
        Receptionist receptionistToRemove = receptionistService.findById(id);
        ReceptionistInfo removedReceptionist = ReceptionistMapper.INSTANCE.mapFromEntityToReceptionistInfo(receptionistService.delete(receptionistToRemove));

        return ResponseEntity.ok(removedReceptionist);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReceptionistInfo>> getAllReceptionist(){
        var allReceptionist = receptionistService.findAll().stream()
                .map(ReceptionistMapper.INSTANCE::mapFromEntityToReceptionistInfo)
                .toList();

        return ResponseEntity.ok(allReceptionist);
    }
}
