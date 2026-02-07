package com.eventdriver.ejercicios.quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mensajes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 * Recurso JAX-RS para enviar mensajes que serán publicados en Kafka.
 * <p>
 * Expone el endpoint POST /mensajes que acepta un objeto {@link Message} y
 * delega en {@link MensajesService} para convertir y enviar el mensaje al
 * binding de salida configurado (SmallRye Reactive Messaging).
 * </p>
 * <p>
 * Devuelve un DTO simple con el resultado de la operación para facilitar
 * pruebas vía curl o Postman.
 * </p>
 */
public class MensajesResource {

    @Inject
    MensajesService mensajesService;

    @POST
    public Response enviarMensaje(Message mensaje) {
        try {
            mensajesService.enviarAKafka(mensaje);
            return Response.ok()
                    .entity(new ResponseDTO("Mensaje enviado a Kafka: " + mensaje.getSubject()))
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(new ResponseDTO("Error al enviar mensaje: " + e.getMessage()))
                    .build();
        }
    }

    // DTO para respuestas
    public static class ResponseDTO {
        private String mensaje;

        public ResponseDTO(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
