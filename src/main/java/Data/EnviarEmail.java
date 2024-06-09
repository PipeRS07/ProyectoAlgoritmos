package Data;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class EnviarEmail {

    private final String correoRemitente;
    private final String contraseñaRemitente;
    private final String servidor;
    private final String puerto;
    private String prueba;

    public EnviarEmail() {
        this.correoRemitente = "proyectoprogra02@gmail.com";
        this.contraseñaRemitente = "vqbi yaak umcy oqbg";
        this.servidor = "smtp.gmail.com";
        this.puerto = "587";
    }


    public void enviarCorreo(String correoDestinatario1, String asunto1, String contenido1, String nombrePDF){

        // Configurar destinatario, asunto y contenido del correo
        String correoDestinatario = correoDestinatario1;
        String asunto = asunto1;
        String contenido = contenido1;
// Configurar la ruta del archivo PDF
        String rutaPDF = "pdfs/"+ "Parcial 2 Solución"+".pdf";
        //String rutaPDF = "";

        // Enviar el correo con el archivo adjunto
        enviarCorreoConAdjunto(correoDestinatario, asunto, contenido, rutaPDF);
    }
    private void enviarCorreoConAdjunto(String correoDestinatario, String asunto, String contenido, String rutaAdjunto) {
        Properties propiedades = System.getProperties();
        propiedades.put("mail.smtp.host", servidor);
        propiedades.put("mail.smtp.port", puerto);
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        Session sesion = Session.getDefaultInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoRemitente, contraseñaRemitente);
            }
        });

        try {
            // Crear un objeto MimeMessage
            MimeMessage mensaje = new MimeMessage(sesion);

            // Configurar el remitente y destinatario
            mensaje.setFrom(new InternetAddress(correoRemitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));

            // Configurar el asunto y el contenido
            mensaje.setSubject(asunto);

            // Crear el cuerpo del mensaje
            BodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setText(contenido);

            // Crear el adjunto
            BodyPart adjunto = new MimeBodyPart();
            File archivoAdjunto = new File(rutaAdjunto);

            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivoAdjunto)));
            adjunto.setFileName(archivoAdjunto.getName());

            // Crear el multipart para agregar cuerpo y adjunto
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoMensaje);
            multipart.addBodyPart(adjunto);

            // Asignar el multipart al mensaje
            mensaje.setContent(multipart);

            // Enviar el mensaje
            Transport.send(mensaje);

            System.out.println("Correo con adjunto enviado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
