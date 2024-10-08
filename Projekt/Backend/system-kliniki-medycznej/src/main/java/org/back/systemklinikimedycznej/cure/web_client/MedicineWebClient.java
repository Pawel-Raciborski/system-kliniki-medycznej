package org.back.systemklinikimedycznej.cure.web_client;

import org.back.systemklinikimedycznej.config.util.RPLApiEndpointUtil;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.dto.MedicineListDto;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class MedicineWebClient {
    private final WebClient webClient;

    public MedicineWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(RPLApiEndpointUtil.MEDICINAL_PRODUCTS_SEARCH_ENDPOINT)
                .build();
    }

    public MedicineListDto findByName(String name, int pageSize, int page, String sort){
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/public")
                        .queryParam("name",name)
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
}
