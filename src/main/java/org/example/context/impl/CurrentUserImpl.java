package org.example.context.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.context.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class CurrentUserImpl implements CurrentUser {

    @Override
    public String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
