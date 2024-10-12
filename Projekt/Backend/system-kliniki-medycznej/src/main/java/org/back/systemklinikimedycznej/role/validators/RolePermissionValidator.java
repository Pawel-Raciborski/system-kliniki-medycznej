package org.back.systemklinikimedycznej.role.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.RolePermissionDto;
import org.back.systemklinikimedycznej.role.exceptions.RolePermissionException;
import org.back.systemklinikimedycznej.role.repository.RolePermissionRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;
import org.back.systemklinikimedycznej.role.util.RolePermissionManagerUtil;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolePermissionValidator {
    private final RolePermissionRepository rolePermissionRepository;


    public void validateRolePermissionNotExist(RolePermission rolePermissionToValidate) {
        Example<RolePermission> example = Example.of(rolePermissionToValidate);

        checkNotExist(rolePermissionRepository.findOne(example).isPresent());

    }

    private void checkNotExist(boolean rolePermissionExist) {
        if(rolePermissionExist){
            throw new RolePermissionException("Podana rola posiada już tę permisję!", HttpStatus.CONFLICT);
        }
    }
}
