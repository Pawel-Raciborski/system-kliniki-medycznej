package org.back.systemklinikimedycznej.role.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDetails;
import org.back.systemklinikimedycznej.role.controller.dto.RolePermissionDto;
import org.back.systemklinikimedycznej.role.exceptions.RolePermissionException;
import org.back.systemklinikimedycznej.role.mapper.PermissionMapper;
import org.back.systemklinikimedycznej.role.repository.RolePermissionRepository;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;
import org.back.systemklinikimedycznej.role.util.RolePermissionManagerUtil;
import org.back.systemklinikimedycznej.role.validators.RolePermissionValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionService {
    private final RolePermissionRepository rolePermissionRepository;
    private final RolePermissionValidator rolePermissionValidator;
    private final RoleService roleService;
    private final PermissionService permissionService;

    @Transactional
    public List<Permission> create(RoleDetails roleDetails) {
        Role role = roleService.findById(roleDetails.role().id());
        List<Permission> permissions = roleDetails.permissions()
                .stream()
                .map(PermissionMapper.INSTANCE::mapToEntity)
                .toList();

        List<RolePermission> rolePermissionToCreate = permissions.stream()
                .map(p -> RolePermissionManagerUtil.buildRolePermission(role,p))
                .toList();

        rolePermissionToCreate.forEach(rolePermissionValidator::validateRolePermissionNotExist);

        List<RolePermission> rolePermissions = rolePermissionRepository.saveAll(rolePermissionToCreate);
        return rolePermissions.stream().map(RolePermission::getPermission).toList();
    }

    @Transactional
    public Permission delete(String roleName, String permissionName) {
        Role role = roleService.findByName(roleName);
        Permission permissionToRemove = permissionService.findByName(permissionName);

        RolePermission rolePermissionToRemove = findByRoleAndPermission(role,permissionToRemove);
        rolePermissionRepository.delete(rolePermissionToRemove);
        return rolePermissionToRemove.getPermission();
    }

    private RolePermission findByRoleAndPermission(Role role, Permission permission) {
        return rolePermissionRepository.findByRoleAndPermission(role,permission)
                .orElseThrow(() -> new RolePermissionException("Nie znaleziono elementu!", HttpStatus.NOT_FOUND));
    }
}
