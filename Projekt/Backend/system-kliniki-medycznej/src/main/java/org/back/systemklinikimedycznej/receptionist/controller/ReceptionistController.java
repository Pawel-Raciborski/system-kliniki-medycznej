package org.back.systemklinikimedycznej.receptionist.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistDetails;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistDto;
import org.back.systemklinikimedycznej.receptionist.controller.dto.ReceptionistInfo;
import org.back.systemklinikimedycznej.receptionist.controller.dto.RegisterReceptionistForm;
import org.back.systemklinikimedycznej.receptionist.mapper.ReceptionistMapper;
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

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestParam(name = "email") String email
    ) {
        receptionistService.delete(email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReceptionistInfo>> getAllReceptionist(){
        var allReceptionist = receptionistService.findAll().stream()
                .map(ReceptionistMapper.INSTANCE::mapFromEntityToReceptionistInfo)
                .toList();

        return ResponseEntity.ok(allReceptionist);
    }
}
