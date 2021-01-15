package com.test.rh.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(value = "/v1")
public class SourceController {

    @Value("${config.url.github}")
    private String urlProject;

    @GetMapping("/")
    @ApiOperation(value = "Redireciona para a documentação")
    public void home(HttpServletResponse response) throws IOException {

        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/source")
    @ApiOperation(value = "Url do projeto no github")
    public String getSource(){
        return urlProject;
    }
}
