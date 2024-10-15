package org.back.systemklinikimedycznej.auth.mapper;

import org.back.systemklinikimedycznej.auth.controller.dto.LoginDataDto;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginDataMapper {

    LoginDataMapper INSTANCE = Mappers.getMapper(LoginDataMapper.class);

    LoginDataDto mapFromDomain(LoginData loginData);
}
