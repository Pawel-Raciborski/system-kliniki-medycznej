package org.back.systemklinikimedycznej.role.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.dto.RoleCreateDto;
import org.back.systemklinikimedycznej.role.repository.RoleRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.util.RoleManagementUtil;
import org.back.systemklinikimedycznej.role.validators.RoleValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleValidator roleValidator;
    private final RoleRepository roleRepository;

    @Transactional
    public Role create(RoleCreateDto roleCreateDto) {
        roleValidator.validateNotExistRoleWithName(roleCreateDto.roleName());

        Role roleToCreate = RoleManagementUtil.buildRoleWithName(roleCreateDto);

        return roleRepository.save(roleToCreate);
    }
}
