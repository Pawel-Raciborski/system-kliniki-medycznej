package org.back.systemklinikimedycznej.role.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.exceptions.PermissionException;
import org.back.systemklinikimedycznej.role.repository.PermissionRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PermissionValidator {
    private final PermissionRepository permissionRepository;

    public void validatePermissionNotExist(PermissionDto permissionDto){
        Optional<Permission> permissionOptional = permissionRepository.findByName(permissionDto.name());

        checkNotExistPermission(permissionOptional.isPresent());
    }

    private void checkNotExistPermission(boolean permissionExist) {
        if(permissionExist){
            throw new PermissionException("Podana permisja już istnieje!", HttpStatus.CONFLICT);
        }
    }
}
