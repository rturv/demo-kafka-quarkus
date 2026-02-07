# kafka-example-quarkus

Ejemplo Quarkus que demuestra integración con Kafka mediante SmallRye
Reactive Messaging y un endpoint REST para publicar mensajes.

## Qué hace este proyecto

- Expone el endpoint `POST /mensajes` para enviar mensajes JSON.
- Publica los mensajes en Kafka usando el binding `mensajes-out`.
- Tiene un consumidor opcional vinculado a `mensajes-in` para procesar
  mensajes recibidos.

## Requisitos

- JDK 17+ (recomendado JDK 21 para compatibilidad con el curso)
- Maven 3.6+
- Kafka (broker) accesible desde la máquina de desarrollo

## Cómo ejecutar

Modo desarrollo (hot-reload):

```bash
./mvnw quarkus:dev
```

Compilar y empaquetar (sin tests):

```bash
./mvnw -DskipTests package
```

Ejecutar jar:

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

## Configuración (archivo `application.properties`)

Archivo: [src/main/resources/application.properties](src/main/resources/application.properties)

Propiedades clave:

- `kafka.bootstrap.servers`: dirección de brokers Kafka (por ejemplo `localhost:9092`).
- `mensajes.topic`: nombre del topic utilizado por el ejemplo (por ejemplo `mensajes`).
- `mp.messaging.outgoing.mensajes-out.connector`: conector saliente (smallrye-kafka).
- `mp.messaging.outgoing.mensajes-out.topic`: topic de salida.
- `mp.messaging.outgoing.mensajes-out.bootstrap.servers`: bootstrap servers para salida.
- `mp.messaging.incoming.mensajes-in.connector`: conector entrante (smallrye-kafka).
- `mp.messaging.incoming.mensajes-in.topic`: topic de consumo.
- `mp.messaging.incoming.mensajes-in.auto.offset.reset`: `earliest` o `latest`.
- `mensajes.consumidor.enabled`: `true` para activar el consumidor integrado.

Ejemplo mínimo:

```properties
kafka.bootstrap.servers=localhost:9092
mensajes.topic=mensajes
mp.messaging.outgoing.mensajes-out.connector=smallrye-kafka
mp.messaging.outgoing.mensajes-out.topic=${mensajes.topic}
mp.messaging.outgoing.mensajes-out.bootstrap.servers=${kafka.bootstrap.servers}
mp.messaging.incoming.mensajes-in.connector=smallrye-kafka
mp.messaging.incoming.mensajes-in.topic=${mensajes.topic}
mp.messaging.incoming.mensajes-in.auto.offset.reset=earliest
mensajes.consumidor.enabled=true
```

Notas:

- Si el broker requiere autenticación o configuración avanzada (SASL/SSL),
  añade las propiedades pertinentes del conector Kafka en `application.properties`.
- Para desactivar el consumidor y solo publicar, ponga `mensajes.consumidor.enabled=false`.

## Ejemplos prácticos

Enviar un mensaje (curl):

```bash
curl -X POST http://localhost:8080/mensajes \
  -H "Content-Type: application/json" \
  -d '{"subject":"hola","body":"mensaje de prueba"}'
```

Usar kafka-console-consumer para ver mensajes:

```bash
kafka-console-consumer --bootstrap-server localhost:9092 --topic mensajes --from-beginning
```

## Generar Javadoc

```bash
./mvnw javadoc:javadoc -pl :kafka-example-quarkus
```

El Javadoc se generará en `target/site/apidocs`.

## Docker / Compose (ejemplo rápido)

Se incluye un ejemplo de `docker-compose` para levantar Kafka en desarrollo
en `docker-compose.kafka.yml`.

## Tests

Ejecuta los tests con:

```bash
./mvnw test
```

## Troubleshooting

- Si no llegan mensajes, comprueba `kafka.bootstrap.servers` y que el topic exista.
- Verifica logs en la salida de Quarkus y en el broker Kafka.
