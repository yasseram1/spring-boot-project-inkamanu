package com.app.InkaManu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inkamanu")
public class AdminController {
    
    @GetMapping("/lista")
    public String listaProductos() {

        

        return "listaProductos";
    }

}
