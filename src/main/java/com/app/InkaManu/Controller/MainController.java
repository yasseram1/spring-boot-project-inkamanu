package com.app.InkaManu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Entity.Usuario;
import com.app.InkaManu.Model.Repository.ProductoRepository;
import com.app.InkaManu.Model.Service.ProductoService;
import com.app.InkaManu.Model.Service.UsuarioService;
import com.google.j2objc.annotations.AutoreleasePool;

@Controller
public class MainController {
	
    
    @GetMapping("/")
    public String main(Model m) {
        m.addAttribute("titulo", "InkaManu");
        return "index";
    }

    /* ESTA VISTA ES todo aquel que quiera conocer la pagina 
	la idea es hacer validaciones de un ejemplo if(usuario == null) 
	no se podra comprar y si el cliente es lo contrario osea si 
	esta registrado podra comprar, esa seria la idea para usar solo 
	una vista de html, tambien se puede duplicar esta vista y mandar el 
	id de un metodo a otro, todo depende de como quieran trabajar 
	al final es lo mismo
	*/
    @GetMapping({"/prueba22" })
	public String prueba22(Model model) {
		return "index";
	}
	
	/* ESTA VISTA ES PARA QUE EL CLIENTE PUEDA 
	VER CON MAS DETALLE UN PRODUCTO DESPUES DE SELECCIONARLO 
	DE LA VISTA vista_producto O DE LA VISTA index, 
	aqui podra visualizar el nombre, precio, descripción, stock 
	y ver la imagen un pcoo mas grande y podra agregarlo al carrito
	*/
	@GetMapping({"/prueba23" })
	public String prueba23(Model model) {

		return "detalle_producto";
	}
	
	/* ESTA VISTA ES PARA QUE EL CLIENTE PUEDA 
	COMPRAR EN NUESTRA EMPRESA
	*/
	@GetMapping({"/prueba24" })
	public String prueba24(Model model) {		
		return "carrito";
	}
	/* ESTA VISTA ES PARA QUE QUE TODOS PUEDAN 
	VISUALIZAR EL CATALOGO DE PRODUCTOS DE UNA MANERA MAS AMPLIA, 
	EN EL INDEX ESTA UN CARRUSEL DE CATALOGOO PERO AHI SE PUEDE VER 
	DE 3 EN 3 EN CAMBIO EN ESTA VISTA SE PUEDE VER TODOS LOS 
	PRODUCTOS Y HAY UN BUSCADOR
	*/
	@GetMapping({"/prueba25" })
	public String prueba25(Model model) {		
		return "vista_producto";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA REGISTRAR
	LOS DATOS DE UN PRODUCTO, ESTA VISTA PROVIENE DE LA VISTA 
	listar_producto
	*/
	@GetMapping({"/prueba26" })
	public String prueba26(Model model) {		
		return "registrar_producto";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA VER LA LISTA
	DE TODOS LOS PRODUCTOS REGISTRADOS EN NUESTRA EMPRESA, DONDE 
	PODRA BUSCAR, AGREGAR, EDITAR Y ELIMINAR LOS DATOS DE UN PRODUCTO
	*/
	
	@Autowired
	private ProductoService productoService;
	@GetMapping({"/prueba27" })
	public String prueba27(Model model) {		
	  List<Producto> listaProducto = productoService.getAllProducts();
	  model.addAttribute("titulo","Lista de Productos");
	  model.addAttribute("productos",listaProducto);
	  return "listar_producto";
	}

	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA VER LA LISTA
	DE TODOS LOS CLIENTES REGISTRADOS EN NUESTRA EMPRESA, DONDE 
	PODRA BUSCAR, AGREGAR, EDITAR Y ELIMINAR LOS DATOS DE UN CLIENTE
	*/
	@Autowired
	private UsuarioService usuarioService;
	@GetMapping({"/prueba28" })
	public String prueba28(Model model) {		
		List<Usuario> listaClientes = usuarioService.getAllUsers();
		model.addAttribute("titulo","Lista de Clientes");
	  	model.addAttribute("clientes",listaClientes);
		return "listar_cliente";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA REGISTRAR 
	LOS DATOS DE UN CLIENTE COMO NOMBRE, APELLIDO PATERNO , ETC, ESTA
	 *  VISTA PROVIENE DE LA VISTA LISTAR_CLIENTE
	*/
	@GetMapping({"/prueba29" })
	public String prueba29(Model model) {		
		return "registrar_cliente";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA ACTUALIZAR 
	LOS DATOS DE UN CLIENTE COMO NOMBRE, APELLIDO PATERNO , ETC, ESTA
	 *  VISTA PROVIENE DE LA VISTA LISTAR_CLIENTE
	*/
	@GetMapping({"/prueba30" })
	public String prueba30(Model model) {		
		return "editar_cliente";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA ACTUALIZAR ALGUN PRODUCTO
	 * COMO SU PRECIO, DESCRIPCION, NOMBRE FOTO, ETC, ESTA
	 *  VISTA PROVIENE DE LA VISTA LISTAR_PRODUCTO
	*/
	@GetMapping({"/prueba31" })
	public String prueba31(Model model) {		
		return "editar_producto";
	}
	/* ESTA VISTA ES PARA QUE EL CLIENTE PUEDA VER SUS 
	DATOS PERSONALES QUE PUSO CUANDO SE REGISTRO 
	EN NUESTRA EMPRESA*/
	@GetMapping({"/prueba32" })
	public String prueba32(Model model) {		
		return "perfil_cliente";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA VER TODAS 
	LAS COMPRAS DE LA EMPRESA Y PUEDA ATENDERLAS, AQUI EL ADMINISTRADOR 
	PODRA BUSCAR TAMBIEN CADA COMPRA DE ACUERDO AL CODIGO DE COMPRA*/
	@GetMapping({"/prueba33" })
	public String prueba33(Model model) {		
		return "listar_compras";
	}
	/* ESTA VISTA ES PARA QUE EL ADMINISTRADOR PUEDA ATENDER 
	UNA COMPRA QUE FUE SELECCIONADA DE LA VSTA LISTAR_COMPRA */
	@GetMapping({"/prueba34" })
	public String prueba34(Model model) {		
		return "atender_compra";
	}
	/* ESTA VISTA ES PARA QUE EL CLIENTE PUEDA VER TODAS SUS 
	COMPRAS EN UNA TABLA */
	@GetMapping({"/prueba35" })
	public String prueba35(Model model) {		
		return "todas_mis_compras";
	}
	/* ESTA VISTA ES PARA QUE EL CLIENTE PUEDA VER UNA DE SUS 
	COMPRAS DE FORMAS MAS DETALLADA, ES COMO CUANDO 
	COMPRO EN EL CARRITO */
	@GetMapping({"/prueba36" })
	public String prueba36(Model model) {		
		return "detalle_de_mi_compra";
	}

	/* ESTA VISTA SERIA PARA EL ADMINISTARDOR DESDE AHI 
	PODRA IR A LA LISTA DE CLIENTES REGITSRADOS, A LA LISTA 
	DE PRODUCTOS Y A LA LISTA DE COMPRAS 
	DONDE PODRA ATENDER ESA COMPRA, EN LA VISTA DE LISTAR PRODUCTO PODRA
	AGREGAR, EDITAR Y ELIMINAR PRODUCTO, Y EN LA LISTA DE CLIENTE DE IGUAL FORMA
	TAMBIEN EL ADMIN PODRA VER SUS DATOS PERSONALES*/
	@GetMapping({"/prueba37" })
	public String prueba37(Model model) {		
		return "inicio_admin";
	}

	@GetMapping({"/pruebaJESUS" })
	public String pruebaJESUS(Model model) {		
		return "prueba_jesus_plantilla";
	}

}
