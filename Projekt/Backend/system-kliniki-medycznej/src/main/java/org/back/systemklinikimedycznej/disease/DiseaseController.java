package org.back.systemklinikimedycznej.disease;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.disease.dto.DiseaseDto;
import org.back.systemklinikimedycznej.disease.dto.SearchDisease;
import org.back.systemklinikimedycznej.disease.mapper.DiseaseMapper;
import org.back.systemklinikimedycznej.disease.services.DiseaseService;
import org.back.systemklinikimedycznej.model.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diseases")
@RequiredArgsConstructor
public class DiseaseController {
    private final DiseaseService diseaseService;


    @PostMapping
    public ResponseEntity<List<DiseaseDto>> findDiseases(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestBody SearchDisease searchDisease
    ){
        Pagination pagination = new Pagination(page,pageSize);
        var diseases = diseaseService.searchDiseases(searchDisease,pagination).stream()
                .map(DiseaseMapper.INSTANCE::mapToDto)
                .toList();

        return ResponseEntity.ok(diseases);
    }
}
