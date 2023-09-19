package com.app.InkaManu.Model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioDTO {

    @NotNull
    @NotBlank(message = "El campo de nombre es obligatorio")
    private String nombre;

    @NotNull
    @NotBlank(message = "El campo de apellidos es obligatorio")
    private String apellidos;

    @NotNull
    @NotBlank(message = "El campo de email es obligatorio")
    private String email;

    @NotNull
    @NotBlank(message = "El campo de contraseña es obligatorio")
    private String password;

    @NotNull
    @NotBlank(message = "El campo de número telefonico es obligatorio")
    private String numTelefonico;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTelefonico() {
        return this.numTelefonico;
    }

    public void setNumTelefonico(String numTelefonico) {
        this.numTelefonico = numTelefonico;
    }

}
