package org.back.systemklinikimedycznej.cure.web_client;

import org.back.systemklinikimedycznej.config.util.RPLApiEndpointUtil;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.dto.MedicineListDto;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.cure.web_client.enums.SpecimenType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;
import java.util.Map;

@Service
public class MedicineWebClient {
    private final WebClient webClient;

    public MedicineWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(RPLApiEndpointUtil.MEDICINAL_PRODUCTS_SEARCH_ENDPOINT + "?specimenTypeEnum={specimenType}")
                .defaultUriVariables(Map.of(
                        "specimenType", SpecimenType.HUMAN.getName()
                ))
                .build();
    }

    public MedicineListDto findByName(String name, int pageSize, int page, String sort){
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/public")
                        .queryParam("name",name)
                        .queryParam("specimenTypeEnum", SpecimenType.HUMAN.getName())
                        .queryParam("subjectRolesIds",1)
                        .queryParam("isAdvancedSearch",false)
                        .queryParam("size",pageSize)
                        .queryParam("page",page)
                        .queryParam("sort",sort).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MedicineListDto.class)
                .block();


    }

    public MedicineListDto findByAtcCode(String atcCode) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/public")
                        .queryParam("atcCode",atcCode)
                        .queryParam("specimenTypeEnum", SpecimenType.HUMAN.getName())
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MedicineListDto.class)
                .block();
    }

    public MedicineListDto findByCommonName(String commonName) {
        return null;
    }

    public MedicineListDto findByRegistryNumber(String registryNumber) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/public")
                        .queryParam("registryNumber",registryNumber)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MedicineListDto.class)
                .block();
    }
}
