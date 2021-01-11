package com.test.rh.controller;

import com.test.rh.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@CrossOrigin(value = "*")
public class LoginController {

    @Autowired
    private UserService service;

    @PostMapping
    @ApiOperation(value="loga um usuário")// verificar necessidade
    public ResponseEntity create(@Valid @RequestBody String dto) throws LoginException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.login(dto));
    }
}
