package com.test.rh.controller;

import com.test.rh.domain.User;
import com.test.rh.service.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/v1/u")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    public UserService service;

    @GetMapping("/listar")
    @ApiOperation(value="Lista todos os usuários cadastrados na base")
    public ResponseEntity getAllUsers(){

        List<User> usrs = service.findAllUser();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usrs);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="retorna o usuário solicitado pelo id")
    public ResponseEntity getUser(@PathVariable(value = "id") Long id){

        try {
            User user = service.getUser(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);
        } catch (NotFoundException e){

            log.warn(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("");
        }
    }

    @PostMapping("/cadastrar")
    @ApiOperation(value="Cadastra um usuário")
    public ResponseEntity userRegister(@Valid @RequestBody User user){

        service.registerUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }

    @PutMapping("/alterar")
    @ApiOperation(value="Altera um usuário")
    public ResponseEntity updateUser(@Valid @RequestBody User user){

        service.updateUser(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @DeleteMapping("/deletar")
    @ApiOperation(value="Deleta um usuário")
    public ResponseEntity deleteUser(@RequestBody Long id) {

        service.deleteUser(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }
}
