package com.eventdriver.ejercicios.quarkus;

/**
 * Representa un mensaje simple con asunto y cuerpo que puede ser enviado
 * al sistema de mensajería (Kafka). Esta clase se serializa a JSON
 * antes de publicarla en el topic configurado.
 */
public class Message {
    private String subject;
    private String body;

    public Message() {
    }

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
