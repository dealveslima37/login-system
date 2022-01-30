package com.cromms.loginsystem.services;

import com.cromms.loginsystem.dto.UserDTO;
import com.cromms.loginsystem.models.User;
import com.cromms.loginsystem.models.enums.Profile;
import com.cromms.loginsystem.repositories.UserRepository;
import com.cromms.loginsystem.security.UserSS;
import com.cromms.loginsystem.services.exceptions.AuthorizationException;
import com.cromms.loginsystem.services.exceptions.EmailExistingexception;
import com.cromms.loginsystem.services.exceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    private BCryptPasswordEncoder pe;

    public UserService(UserRepository repository, BCryptPasswordEncoder pe) {
        this.repository = repository;
        this.pe = pe;
    }

    public UserDTO save(UserDTO dto) {

        if (findByEmail(dto.getEmail()) != null) {
            throw new EmailExistingexception("Já existe um usuário cadastrdo com esse email");
        }

        var user = new User(dto.getName(), dto.getEmail(), dto.getPhone(), pe.encode(dto.getPassword()));
        user = repository.save(user);

        return new UserDTO(user);
    }

    public Page<UserDTO> findAll(Pageable pageable) {

        Page<User> users = repository.findAll(pageable);

        return users.map(x -> new UserDTO(x));
    }

    public User find(Long id) {

        UserSS userLoged = UserLogedService.authenticated();

        if (userLoged == null || !userLoged.hasRole(Profile.ADMIN) && !id.equals(userLoged.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<User> user = repository.findById(id);

        return user.orElseThrow(() -> new EntityNotFoundException("Não exsite usuário cadastrado com esse id"));
    }

    public UserDTO findById(Long id) {
        User user = find(id);

        return new UserDTO(user);
    }

    public Page<UserDTO> findByName(Pageable pageable, String name) {
        Page<User> users = repository.findByName(pageable, name);

        return users.map(x -> new UserDTO(x));
    }

    public UserDTO update(UserDTO dto) {
       var user = find(dto.getId());
       var userUpdate = new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), pe.encode(dto.getPassword()));
       user = repository.save(userUpdate);

        return new UserDTO(user);
    }

    public void delete(Long id) {
        UserSS userLoged = UserLogedService.authenticated();

        if (userLoged == null || !id.equals(userLoged.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        repository.deleteById(id);
    }

    public User findByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);

        return user.orElse(null);
    }


}
