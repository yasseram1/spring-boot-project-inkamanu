package com.app.InkaManu.Reportes.Excel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.ClientAuth;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.app.InkaManu.Model.Entity.Usuario;
@Component("/prueba28.xlsx")
public class ClientesExportar extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        response.setHeader("Content-Disposition", "attachment; filename=\"listado-clientes.xlsx\"");
        Sheet hoja = workbook.createSheet("Clientes");

        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);
        celda.setCellValue("Listado General de Clientes");

        Row filaData = hoja.createRow(2);
        String[] columnas = {"ID","NOMBRE","APELLIDOS","EMAIL","CONTRASEÑA","CELULAR"};

        for (int i = 0; i<=columnas.length; i++){
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }

        List<Usuario> listaUsuario = (List<Usuario>) model.get("clientes");

        int numFila = 3;
        for(Usuario usuario : listaUsuario){
            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(usuario.getId());
            filaData.createCell(1).setCellValue(usuario.getNombre());
            filaData.createCell(2).setCellValue(usuario.getApellidos());
            filaData.createCell(3).setCellValue(usuario.getEmail());
            filaData.createCell(4).setCellValue(usuario.getPassword());

            numFila++;
        }

            


    }
    
}
