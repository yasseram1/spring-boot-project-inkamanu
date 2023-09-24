package com.app.InkaManu.Controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.InkaManu.Model.DTO.ProductoDTO;
import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Service.ProductoService;

@Controller
@RequestMapping("/inkamanu")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(@Autowired ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping(value = "/admin/formularioCrearProducto")
    public String mostrarFormularioCrearNuevoProducto(Model model) {
        model.addAttribute("productoDTO", new ProductoDTO());
        return "formularios/formularioCrearProducto";
    }

    @PostMapping("/admin/guardarProductoNuevo")
    public String guardarProductoNuevo(@Valid @ModelAttribute ProductoDTO productoDTO, BindingResult result,
            Model model, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            return "formularios/formularioCrearProducto";
        }

        Producto p = new Producto();
        p.setDescripcion(productoDTO.getDescripcion());
        p.setFechaActualizacion(null);
        p.setFechaCreacion(LocalDateTime.now());

        String imgStrg = "agregar funcionalidad para subir imagen a onedrive";

        p.setImagen(imgStrg);
        p.setNombre(productoDTO.getNombre());
        p.setPrecio(productoDTO.getPrecio());
        p.setStock(productoDTO.getStock());

        productoService.saveProduct(p);
        redirectAttrs.addFlashAttribute("success", "Producto guardado con éxito");
        return "redirect:/inkamanu/admin/formularioCrearProducto";
    }

}
