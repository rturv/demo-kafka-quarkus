package com.eventdriver.ejercicios.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;
import io.quarkus.arc.properties.IfBuildProperty;

/**
 * Consumidor de mensajes desde Kafka ligado al canal `mensajes-in`.
 * <p>
 * Si la propiedad de build `mensajes.consumidor.enabled` es `true`, este bean
 * se creará y SmallRye Kafka inicializará el conector. Si es `false`, el bean
 * no se creará y no habrá conexión/consumo alguno.
 * </p>
 */
@ApplicationScoped
@IfBuildProperty(name = "mensajes.consumidor.enabled", stringValue = "true")
public class MensajesConsumer {

    private static final Logger LOG = Logger.getLogger(MensajesConsumer.class);


    @Incoming("mensajes-in")
    public void consumirMensaje(String mensaje) {
            LOG.infof("Mensaje recibido de Kafka: %s", mensaje);
    }
}
