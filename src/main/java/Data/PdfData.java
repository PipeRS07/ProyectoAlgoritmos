package Data;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import domain.clasesBase.Reporte;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfData {
    private String nombreArchivo;

    public PdfData(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void initDocument(Reporte reporte) {
        Document document = new Document(PageSize.A4);

        try {
            // Crear la carpeta si no existe
            Path folderPath = Paths.get("pdfs");
            Files.createDirectories(folderPath);

            // Crear el archivo de salida en la carpeta "pdfs"
            Path filePath = Paths.get("pdfs", nombreArchivo);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath.toString()));

            // Abrir el documento para escritura
            document.open();

            // Agregar el título del reporte
            document.add(new Paragraph("Título: " + reporte.getTitulo()));
            document.add(new Paragraph("Destinatario: " + reporte.getDestinatario()));
            document.add(new Paragraph("Contenido: " + reporte.getContenido()));

            // Crear una página vacía
            document.newPage();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el documento después de agregar contenido
            document.close();
        }
    }

    public void generarYEnviarPDF(Reporte reporte) {
        // Generar el PDF
        initDocument(reporte);

        // Enviar el PDF por correo
        EnviarEmail enviarEmail = new EnviarEmail();
        String asunto = "Reporte: " + reporte.getTitulo();
        String contenido = "Estimado " + reporte.getDestinatario() + ",\n\nAdjunto encontrará el reporte solicitado.\n\nSaludos,\nEquipo de Reportes";
        enviarEmail.enviarCorreo(reporte.getDestinatario(), asunto, contenido, nombreArchivo);
    }

    @Override
    public String toString() {
        return "PdfData{" +
                "nombreArchivo='" + nombreArchivo + '\'' +
                '}';
    }
}
