package com.app.InkaManu.Entitys;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


public class Administrador {
    
    private String nombres;	
    private String apellidoPat;
    private String apellidoMat;
    private String dni;
    private String email;
    private String celular;
    private String genero;
    private String password;
    private String repassword;
    private Date fechaNacimiento;
    private Date fechaRegistro;
    private Date fechaActualización;
    
}
