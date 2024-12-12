package com.management.sales.sales.service;

import com.management.sales.sales.model.Invoice;
import com.management.sales.sales.model.InvoiceProduct;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

@Service
public class InvoicePdfService {

    public ByteArrayInputStream generateInvoicePdf(Invoice invoice) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            // Encabezado
            Paragraph title = new Paragraph("Factura")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontColor(ColorConstants.BLACK);
            document.add(title);

            // Información de la factura
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            document.add(new Paragraph("ID de Factura: " + invoice.getId()));
            document.add(new Paragraph("Fecha: " + dateFormat.format(invoice.getDate())));
            document.add(new Paragraph("Cliente: " + invoice.getCustomer().getName()));

            // Tabla de productos
            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2, 2}))
                    .useAllAvailableWidth();
            table.addHeaderCell(new Cell().add(new Paragraph("Producto").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Precio Unitario").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Subtotal").setBold()));

            for (InvoiceProduct product : invoice.getInvoiceProducts()) {
                table.addCell(product.getProduct().getName());
                table.addCell(String.valueOf(product.getQuantity()));
                table.addCell(String.format("%.2f", product.getPrice()));
                table.addCell(String.format("%.2f", product.getQuantity() * product.getPrice()));
            }
            document.add(table);

            // Total
            Paragraph total = new Paragraph("Total: $" + String.format("%.2f", invoice.getTotalPrice()))
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(total);

            // Pie de página
            Paragraph footer = new Paragraph("Gracias por su compra.")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(ColorConstants.GRAY);
            document.add(footer);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
