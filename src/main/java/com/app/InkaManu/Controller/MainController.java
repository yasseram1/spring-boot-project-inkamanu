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

	@GetMapping({"/prueba25" })
	public String prueba25(Model model) {		
		return "vista_producto";
	}

	@GetMapping({"/prueba26" })
	public String prueba26(Model model) {		
		return "registrar_producto";
	}

	@GetMapping({"/prueba27" })
	public String prueba27(Model model) {		
		return "listar_producto";
	}

	@GetMapping({"/prueba28" })
	public String prueba28(Model model) {		
		return "listar_cliente";
	}

	@GetMapping({"/prueba29" })
	public String prueba29(Model model) {		
		return "registrar_cliente";
	}

	@GetMapping({"/prueba30" })
	public String prueba30(Model model) {		
		return "editar_cliente";
	}

	@GetMapping({"/prueba31" })
	public String prueba31(Model model) {		
		return "editar_producto";
	}

	@GetMapping({"/prueba32" })
	public String prueba32(Model model) {		
		return "perfil_cliente";
	}

	@GetMapping({"/prueba33" })
	public String prueba33(Model model) {		
		return "listar_compras";
	}

	@GetMapping({"/prueba34" })
	public String prueba34(Model model) {		
		return "atender_compra";
	}

	@GetMapping({"/prueba35" })
	public String prueba35(Model model) {		
		return "todas_mis_compras";
	}

	@GetMapping({"/prueba36" })
	public String prueba36(Model model) {		
		return "detalle_de_mi_compra";
	}
}
