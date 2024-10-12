package org.back.systemklinikimedycznej.role.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.exceptions.PermissionException;
import org.back.systemklinikimedycznej.role.repository.PermissionRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.back.systemklinikimedycznej.role.util.PermissionManagerUtil;
import org.back.systemklinikimedycznej.role.validators.PermissionValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionValidator permissionValidator;
    private final PermissionRepository permissionRepository;

    public Permission findByName(String name) {
        return permissionRepository.findByName(name)
                .orElseThrow(() -> new PermissionException("Nie znaleziono permisji o nazwie [%s]".formatted(name), HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Permission create(PermissionDto permission) {
        permissionValidator.validatePermissionNotExist(permission);

        Permission permissionToCreate = PermissionManagerUtil.buildPermission(permission);

        return permissionRepository.save(permissionToCreate);
    }

    public Permission delete(String permissionName) {
        Permission permissionToRemove = findByName(permissionName);
        permissionRepository.delete(permissionToRemove);

        return permissionToRemove;
    }
}
