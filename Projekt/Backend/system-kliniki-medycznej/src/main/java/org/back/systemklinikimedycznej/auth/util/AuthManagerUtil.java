package org.back.systemklinikimedycznej.auth.util;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AuthManagerUtil {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";


    public static LoginData buildLoginData(Account account, List<Role> roles) {
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",UUID.randomUUID());
        map.put("username",account.getUsername());
        map.put("email",account.getEmail());
        map.put("roles",roles.stream().map(RoleMapper.INSTANCE::mapFromEntity).toList());


        return LoginData.builder().data(map)
//                .sessionId(UUID.randomUUID())
//                .username(account.getUsername())
//                .email(account.getEmail())
//                .roles(roles)
                .build();
    }
}
