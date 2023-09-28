package com.app.InkaManu.Reportes.PDF;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Entity.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/prueba27")
public class ProductosExporPDF extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        List<Producto> listaProducto = (List<Producto>) model.get("productos");//productos del model.attribute("productos", listaProducto)
        
        document.setPageSize(PageSize.A4);
        document.open();   

        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Listado de Usuarios"));

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaProducto = new PdfPTable(6);
        
        listaProducto.forEach(producto ->{
            tablaProducto.addCell(String.valueOf(producto.getId()));
            tablaProducto.addCell(producto.getNombre());
            tablaProducto.addCell(producto.getDescripcion());
            tablaProducto.addCell(String.valueOf(producto.getPrecio()));
            tablaProducto.addCell(String.valueOf(producto.getStock()));
            tablaProducto.addCell(String.valueOf(producto.getFechaCreacion()));
            tablaProducto.addCell(String.valueOf(producto.getFechaActualizacion()));
        });

        document.add(tablaTitulo);
        document.add(tablaProducto);

    }
}
