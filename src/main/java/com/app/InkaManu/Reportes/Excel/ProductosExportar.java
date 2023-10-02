package com.app.InkaManu.Reportes.Excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Entity.Usuario;

@Component("/prueba27.xlsx")
public class ProductosExportar extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        response.setHeader("Content-Disposition", "attachment; filename=\"listado-productos.xlsx\"");
        Sheet hoja = workbook.createSheet("Productos");

        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);
        celda.setCellValue("Listado General de Productos");

        Row filaData = hoja.createRow(2);
        String[] columnas = {"ID","NOMBRE","DESCRIPCIÓN","PRECIO","FECHADECREACIÓN","FECHADEACTUALIZACIÓN"};

        for (int i = 0; i<=columnas.length; i++){
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }

        List<Producto> listaProducto= (List<Producto>) model.get("clientes");

        int numFila = 3;
        for(Producto producto : listaProducto){
            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(producto.getId());
            filaData.createCell(1).setCellValue(producto.getNombre());
            filaData.createCell(2).setCellValue(producto.getDescripcion());
            filaData.createCell(3).setCellValue(producto.getPrecio());
            filaData.createCell(4).setCellValue(producto.getStock());
            filaData.createCell(5).setCellValue(producto.getFechaCreacion());
            filaData.createCell(6).setCellValue(producto.getFechaActualizacion());

            numFila++;
        }
    }
    
}
