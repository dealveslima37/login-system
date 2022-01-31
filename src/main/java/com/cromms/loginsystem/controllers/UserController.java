package com.cromms.loginsystem.controllers;

import com.cromms.loginsystem.dto.UserDTO;
import com.cromms.loginsystem.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Loginsystem")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Cadastrar usuário")
    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) {
        var user = service.save(dto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Buscar todos os usuários")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> users = service.findAll(pageable);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar usuário por id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = service.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Buscar usuário por nome")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/name/{name}")
    public ResponseEntity<Page<UserDTO>> findByName(@PathVariable Pageable pageable, String name) {
        Page<UserDTO> users = service.findByName(pageable, name);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar usuário")
    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) {
        var user = service.update(dto);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar usuário por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
