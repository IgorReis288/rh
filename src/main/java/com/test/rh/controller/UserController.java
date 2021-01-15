package com.test.rh.controller;

import com.test.rh.domain.User;
import com.test.rh.service.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/v1/u")
public class UserController {

    @Autowired
    public UserService service;

    @GetMapping("/listar")
    @ApiOperation(value="Lista todos os usuários cadastrados na base")
    public String getAllUsers(Model model){

        List<User> usrs = service.findAllUser();
        model.addAttribute("userList", usrs);
        return "cadastros";
    }

    @GetMapping("/")
    @ApiOperation(value="retorna o usuário solicitado pelo id")
    public String getUser(@RequestParam("id") Long id, Model model){

        try {
            User user = service.getUser(id);
            model.addAttribute("user", user);
            return "cadastro";
        } catch (NotFoundException e){

            log.warn(e.getMessage());

            return "redirect:/v1/u/listar";
        }
    }

    @GetMapping("/cadastro")
    @ApiOperation(value="Tela de cadastro um usuário")
    public String userForm(Model model, User user){

        model.addAttribute("user", user);
        return "cadastro";
    }



    @PostMapping("/cadastrar")
    public String userRegister(@Valid User user, Model model){

        service.registerUser(user);
        model.addAttribute("success", "criado com sucesso!");
        return "redirect:/v1/u/listar";
    }

    @PostMapping("/alterar")
    @ApiOperation(value="Altera um usuário")
    public String updateUser(@Valid User user){

        service.updateUser(user);

        return "redirect:/v1/u/listar";
    }

    @GetMapping("/deletar")
    @ApiOperation(value="Deleta um usuário")
    public String deleteUser(@RequestParam Long id) {

        service.deleteUser(id);

        return "redirect:/v1/u/listar";
    }
}
