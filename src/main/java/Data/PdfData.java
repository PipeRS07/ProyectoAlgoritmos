package Data;

public class  PdfData {

    //* para instanciar
//    PdfData pdf = new PdfData(nombre((String) this.jCURL.getSelectedItem()));
//
//        pdf.initDocument(crearGrafico());
//
//    EnviadorCorreo correo = new EnviadorCorreo();
//
//        correo.enviarCorreo(this.jTPara.getText(), this.jTAsunto.getText(), this.jTContenido.getText(), (nombre((String) this.jCURL.getSelectedItem())));
//
//    private String nombreArchivo;
//
//    public void initDocument(JFreeChart grafico) {
//        // Crear una instancia de PdfData con el nombre del archivo
//        PdfData pdfData = new PdfData(this.nombreArchivo+".pdf");
//
//        // Inicializar el documento
//        pdfData.initDocument1(grafico);
//
//    }
//
//    public PdfData(String nombreArchivo) {
//        this.nombreArchivo = nombreArchivo;
//
//    }
//
//    public String getNombreArchivo() {
//        return nombreArchivo;
//    }
//
//    public void setNombreArchivo(String nombreArchivo) {
//        this.nombreArchivo = nombreArchivo;
//    }
//
//    private void initDocument1(JFreeChart grafico) {
//        // Crear el documento con tamaño A4
//        Document document = new Document(PageSize.A4);
//
//        try {
//            // Crear la carpeta si no existe
//            Path folderPath = Paths.get("pdfs");
//            Files.createDirectories(folderPath);
//
//            // Crear el archivo de salida en la carpeta "pdfs"
//            Path filePath = Paths.get("pdfs", nombreArchivo);
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath.toString()));
//
//            // Abrir el documento para escritura
//            document.open();
//
//            // Crear una página vacía
//            document.newPage();
//
//            // Convertir el gráfico a una imagen y agregarlo al documento PDF
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ChartUtilities.writeChartAsPNG(baos, grafico, 400, 300); // Ajusta el tamaño según sea necesario
//            Image imagenGrafico = Image.getInstance(baos.toByteArray());
//            document.add(imagenGrafico);
//
//            // Puedes agregar contenido adicional al PDF aquí
//            document.add(new Paragraph("Contenido del PDF"));
//
//        } catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            // Cerrar el documento después de agregar contenido
//            document.close();
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "PdfData{" +
//                "nombreArchivo='" + nombreArchivo + '\'' +
//                '}';
//    }
}
