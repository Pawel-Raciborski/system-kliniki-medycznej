package org.back.systemklinikimedycznej.cure;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.dto.MedicineListDto;
import org.back.systemklinikimedycznej.cure.service.MedicineService;
import org.back.systemklinikimedycznej.cure.web_client.MedicineWebClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicine")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;
    private final MedicineWebClient medicineWebClient;
    @GetMapping
    public ResponseEntity<MedicineListDto> findByName(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "sort") String sort
            ) {
        var listDto = medicineWebClient.findByName(name,size,page,sort);

        return ResponseEntity.ok(listDto);
    }
}
