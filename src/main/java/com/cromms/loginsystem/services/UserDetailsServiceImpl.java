package com.cromms.loginsystem.services;

import com.cromms.loginsystem.models.User;
import com.cromms.loginsystem.repositories.UserRepository;
import com.cromms.loginsystem.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = repository.findByEmail(email);

        if(optional.isEmpty()){
            throw new UsernameNotFoundException("Não existe usuário cadastrado com esse email");
        }

        var user = optional.get();

        String[] roles = user.getProfile().getDescription().equals("ROLE_ADMIN") ? new String[] {"ROLE_ADMIN", "ROLE_USER"} :
                new String[] {"ROLE_USER"};

        return new UserSS(user.getId(), user.getName(), user.getEmail(), user.getPassword(), Arrays.asList(roles));
    }
}
