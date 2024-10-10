package org.back.systemklinikimedycznej.role.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.exceptions.RoleException;
import org.back.systemklinikimedycznej.role.repository.RoleRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleValidator {
    private final RoleRepository roleRepository;

    public void validateNotExistRoleWithName(String roleName) {
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        checkNotExist(optionalRole.isPresent());
    }

    private void checkNotExist(boolean roleExist) {
        if(roleExist){
            throw new RoleException("Rola o podanej nazwie już istnieje!", HttpStatus.CONFLICT);
        }
    }
}
