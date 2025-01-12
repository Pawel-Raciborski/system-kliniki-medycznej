package org.back.systemklinikimedycznej.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class ApplicationUser implements UserDetails {
    private final Account account;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getAccountRoles().stream()
                .map(AccountRole::getRole)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }
}
