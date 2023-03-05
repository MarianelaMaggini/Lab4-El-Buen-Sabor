package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.Factura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.repositories.comprobantes.IFacturaRepository;
import com.example.buensabor.services.mail.FacturaMailService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FacturaService {

    @Autowired
    IFacturaRepository facturaRepository;

    @Autowired
    DetallePedidoService detallePedidoService;

    @Autowired
    FacturaMailService facturaMailService;

    @Value("${spring.mail.username}")
    String mailFrom;

    public List<Factura> getFacturas() {
        return (ArrayList<Factura>) facturaRepository.findAll();
    }

    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    public Optional<Factura> getFacturaByPedidoNumeroPedido(Long numeroPedido){ return facturaRepository.findByPedidoId(numeroPedido); }

    public Factura saveOrUpdateFactura(Factura factura) throws IOException, DocumentException, MessagingException {
        factura.setId(null);
        Factura facturaNueva = facturaRepository.save(factura);
        this.generateFacturaPDF(facturaNueva.getId());
        String mailUsuario = facturaNueva.getPedido().getUsuario().getEmail();
        String subjet = "Envío de comprobante #" + facturaNueva.getId();
        String msg = "Gracias por su compra! \n Equipo del Buen Sabor.";
        this.facturaMailService.sendEmail(mailFrom, mailUsuario, subjet, msg, facturaNueva);
        return facturaNueva;
    }

    public void generateFacturaPDF(Long numeroFactura) throws IOException, DocumentException {
        Factura factura = this.getFacturaById(numeroFactura).get();
        List<DetallePedido> detalles = detallePedidoService.getDetalleByIdPedido(factura.getPedido().getId());
        Document document = new Document();
        Image imagen = Image.getInstance("src//main//resources//images//logo.png");
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        String nombreDocumento = "src//main//resources//files//" + factura.getId() + "_factura.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(nombreDocumento));
        document.open();
        imagen.scaleAbsolute(170f, 150f);
        imagen.setAbsolutePosition(0f, 670f);
        Paragraph parrafo1 = new Paragraph("EL BUEN SABOR S.R.L\n" +
                                                "CUIT: 11456724\n" +
                                                "Teléfono: 261-4978344\n" +
                                                "Dirección: Malvinas Argentinas 445\n" +
                                                "Fecha: " + factura.getFecha().toString() + "\n" +
                                                "Factura #" + factura.getId() + "\n", font);
        parrafo1.setAlignment(Element.ALIGN_RIGHT);
        Chunk tituloTabla = new Chunk("DETALLE DE SU COMPRA", font);
        PdfPTable table = new PdfPTable(3);
        this.addTableHeader(table);
        this.addRows(table, detalles);
        Paragraph parrafo2 = new Paragraph("Descuento: $" + this.calcularTotal(detalles) * factura.getMontoDescuento() + "\n" +
                                                 "Total: $" + this.calcularTotal(detalles) * (1 - factura.getMontoDescuento()) + "\n" +
                                                 "Gracias por su compra!", font);
        document.add(imagen);
        document.add(parrafo1);
        document.add(tituloTabla);
        document.add(table);
        document.add(parrafo2);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Cantidad", "Artículo", "SubTotal").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private void addRows(PdfPTable table, List<DetallePedido> detalles) {
        for (DetallePedido detalle : detalles) {
            table.addCell(String.valueOf(detalle.getCantidad()));
            table.addCell(detalle.getArticulo().getDenominacion());
            table.addCell(String.valueOf(detalle.getSubtotal()));
        }
    }

    private double calcularTotal(List<DetallePedido> detalles) {
        double total = 0;
        for (DetallePedido detalle : detalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }

}
