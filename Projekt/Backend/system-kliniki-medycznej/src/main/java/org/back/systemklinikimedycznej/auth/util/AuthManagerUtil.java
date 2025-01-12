package org.back.systemklinikimedycznej.auth.util;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.auth.domain.ApplicationUser;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AuthManagerUtil {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";


//    public static LoginData buildLoginData(ApplicationUser user) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("sessionId",UUID.randomUUID());
//        map.put("username",user.getUsername());
//        map.put("email",user.getAccount().getEmail());
//        map.put("roles",user.getAccount());
//
//
//        return LoginData.builder().data(map)
//                .build();
//    }
//
//    public static LoginData buildLoginData(Account user) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("sessionId",UUID.randomUUID());
//        map.put("username",user.getUsername());
//        map.put("email",user.getEmail());
//        map.put("roles",user.getAccountRoles().stream().map(a -> a.getRole().getName()).toList());
//
//
//        return LoginData.builder().data(map)
////                .sessionId(UUID.randomUUID())
////                .username(account.getUsername())
////                .email(account.getEmail())
////                .roles(roles)
//                .build();
//    }
}
