package org.back.systemklinikimedycznej.cure.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.dto.MedicineListDto;
import org.back.systemklinikimedycznej.cure.dto.SearchMedicine;
import org.back.systemklinikimedycznej.cure.mapper.MedicineMapper;
import org.back.systemklinikimedycznej.cure.service.MedicineService;
import org.back.systemklinikimedycznej.cure.web_client.MedicineWebClient;
import org.back.systemklinikimedycznej.model.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/search")
    public ResponseEntity<List<MedicineDto>> searchMedicines(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestBody SearchMedicine searchMedicine
            ){
        Pagination pagination = new Pagination(page,pageSize);
        List<MedicineDto> medicines = medicineService.searchMedicine(searchMedicine,pagination);

        return ResponseEntity.ok(medicines);
    }
}
