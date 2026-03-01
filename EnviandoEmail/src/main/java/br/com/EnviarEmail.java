package br.com;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EnviarEmail {
    public static void main(String[] args) {
        // Configurações do servidor SMTP (Ex: Gmail)
        String host = "smtp.gmail.com";
        final String usuario = "exemplo@gmail.com";
        final String senha = ""; // Use "Senha de App" do Google

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS seguro
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Porta TLS

        // Autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            // Criar a mensagem
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("exemplo@gmail.com"));
            message.setSubject("Assunto do E-mail");
            message.setText("Conteúdo do e-mail em texto simples.");

            // Enviar
            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
