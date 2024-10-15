package org.back.systemklinikimedycznej.role.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.exceptions.RoleException;
import org.back.systemklinikimedycznej.role.repository.RoleRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.util.RoleManagementUtil;
import org.back.systemklinikimedycznej.role.validators.RoleValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleValidator roleValidator;
    private final RoleRepository roleRepository;

    @Transactional
    public Role create(RoleDto roleDto) {
        roleValidator.validateNotExistRoleWithName(roleDto.name().toUpperCase());

        Role roleToCreate = RoleManagementUtil.buildRoleWithName(roleDto);

        return roleRepository.save(roleToCreate);
    }

    @Transactional
    public Role update(String previousName, RoleDto roleUpdateData) {
        Role roleToUpdate = findByName(previousName);

        RoleManagementUtil.setFieldsToUpdate(roleToUpdate,roleUpdateData);

        return roleRepository.save(roleToUpdate);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleException("Nie znaleziono roli o podanej nazwie!", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Role delete(String roleName) {
        Role roleToRemove = findByName(roleName);

        roleRepository.delete(roleToRemove);
        return roleToRemove;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
