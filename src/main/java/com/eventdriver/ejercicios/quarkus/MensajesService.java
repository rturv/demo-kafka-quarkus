package com.eventdriver.ejercicios.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;

@ApplicationScoped
/**
 * Servicio de aplicación responsable de serializar y publicar mensajes en Kafka
 * a través del emitter configurado en el binding `mensajes-out`.
 * <p>
 * Este servicio usa {@code ObjectMapper} para convertir el objeto {@link Message}
 * a JSON antes de enviarlo al {@code Emitter<String>}.
 * </p>
 */
public class MensajesService {

    private static final Logger LOG = Logger.getLogger(MensajesService.class);

    /**
     *  El emitter se inyecta usando el binding `mensajes-out` definido en application.properties. 
     *  Este emitter se encargará de enviar los mensajes al topic configurado en el canal 
     *  `mensajes-out` de SmallRye Reactive Messaging, que a su vez se conectará a 
     *  Kafka usando la configuración proporcionada en application.properties.
     */
    @Inject
    @Channel("mensajes-out")//Canal de salida. 
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;

    public void enviarAKafka(MensajeRequest mensaje) throws Exception {
        String jsonMessage = objectMapper.writeValueAsString(mensaje);
        LOG.infof("Enviando mensaje a Kafka: %s", jsonMessage);
        emitter.send(jsonMessage);
    }
}
