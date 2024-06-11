package Data;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
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

    public EnviarEmail() {
        this.correoRemitente = "proyectoprogra02@gmail.com";
        this.contraseñaRemitente = "vqbi yaak umcy oqbg";
        this.servidor = "smtp.gmail.com";
        this.puerto = "587";
    }

    public void enviarCorreo(String correoDestinatario1, String asunto1, String contenido1, String nombrePDF) {
        String correoDestinatario = correoDestinatario1;
        String asunto = asunto1;
        String contenido = contenido1;
        String rutaPDF = "pdfs/" + "Parcial 2 Solución" + ".pdf";

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
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoRemitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);

            // Añadir el archivo adjunto
            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setText(contenido);
            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaAdjunto)));
            adjunto.setFileName(new File(rutaAdjunto).getName());

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoMensaje);
            multipart.addBodyPart(adjunto);
            mensaje.setContent(multipart);

            Transport.send(mensaje);
            System.out.println("Correo con adjunto enviado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para enviar correo sin adjunto
    public void enviarCorreoSinAdjunto(String correoDestinatario, String asunto, String contenido) {
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
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoRemitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);

            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
