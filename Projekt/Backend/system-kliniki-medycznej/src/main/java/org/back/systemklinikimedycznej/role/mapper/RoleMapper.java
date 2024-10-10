package org.back.systemklinikimedycznej.role.mapper;

import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.role.dto.RoleCreateDto;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleCreateDto mapFromEntity(Role role);
}
