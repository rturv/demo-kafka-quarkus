package com.eventdriver.ejercicios.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import java.util.Optional;

@ApplicationScoped
/**
 * Consumidor de mensajes desde Kafka ligado al canal `mensajes-in`.
 * <p>
 * El consumo se activa solo si la propiedad `mensajes.consumidor.enabled` está
 * configurada a true. Los mensajes recibidos se registran en el logger.
 * </p>
 */
public class MensajesConsumer {

    private static final Logger LOG = Logger.getLogger(MensajesConsumer.class);

    @Inject
    @ConfigProperty(name = "mensajes.consumidor.enabled", defaultValue = "false")
    boolean consumerEnabled;

    @Incoming("mensajes-in")
    public void consumirMensaje(String mensaje) {
        if (consumerEnabled) {
            LOG.infof("Mensaje recibido de Kafka: %s", mensaje);
        }
    }
}
