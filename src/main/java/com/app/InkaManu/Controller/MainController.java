package com.app.InkaManu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("/")
    public String main(Model m) {
        m.addAttribute("titulo", "InkaManu");
        return "index";
    }

}
