package com.cromms.loginsystem.services;

import com.cromms.loginsystem.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserLogedService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
