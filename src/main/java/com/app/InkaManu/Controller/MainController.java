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

    @GetMapping({"/prueba22" })
	public String prueba22(Model model) {		
		return "index";
	}
	
	@GetMapping({"/prueba23" })
	public String prueba23(Model model) {		
		return "detalle_producto";
	}
	
	@GetMapping({"/prueba24" })
	public String prueba24(Model model) {		
		return "carrito";
	}

}
