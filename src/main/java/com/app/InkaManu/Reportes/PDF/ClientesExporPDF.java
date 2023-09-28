package com.app.InkaManu.Reportes.PDF;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.InkaManu.Model.Entity.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("/prueba28")
public class ClientesExporPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        List<Usuario> listaUsuario = (List<Usuario>) model.get("usuarios");//usuarios del model.attribute("usuarios", listaUsuario)
        
        document.setPageSize(PageSize.A4);
        document.open();   

        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Listado de Usuarios"));

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaClientes = new PdfPTable(6);
        
        listaUsuario.forEach(usuario ->{
            tablaClientes.addCell(String.valueOf(usuario.getId()));
            tablaClientes.addCell(usuario.getNombre());
            tablaClientes.addCell(usuario.getApellidos());
            tablaClientes.addCell(usuario.getEmail());
            tablaClientes.addCell(usuario.getPassword());
            tablaClientes.addCell(usuario.getNumTelefonico());
        });

        document.add(tablaTitulo);
        document.add(tablaClientes);

    }
}
