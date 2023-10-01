package com.app.InkaManu.Model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ProductoDTO {

    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private MultipartFile imagen;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    private int stock;

    @NotNull(message = "El grado de Alcohol es obligatorio")
    private Double gradoAlcohol;

    @NotBlank(message = "El tipo de cerveza es obligatorio")
    private String tipoCerveza;

    @NotNull(message = "El porcentaje de descuento es obligatorio")
    private Double porcentajeDescuento;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MultipartFile getImagen() {
        return this.imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getGradoAlcohol() {
        return this.gradoAlcohol;
    }

    public void setGradoAlcohol(Double gradoAlcohol) {
        this.gradoAlcohol = gradoAlcohol;
    }

    public String getTipoCerveza() {
        return this.tipoCerveza;
    }

    public void setTipoCerveza(String tipoCerveza) {
        this.tipoCerveza = tipoCerveza;
    }

    public Double getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
