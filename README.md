# Challenge

Problemas que considero en la arquitectura:

1. Supuesto:
-  Suponiendo que la aplicación tiene alta concurrencia, donde tenemos varias intancias de la api rest con un balanceo de carga, le enviamos por n instancias n mensajes al servidor de mensajeria (en este caso RabbitMq).

2. Problema:
- El servidor Rabbit no tiene la capacidad de procesar todos los mensajes al mismo tiempo. Por lo que no se va a procesar todos los request al mismo tiempo.

3. Solución:
- Implementar para el manejo del servidor de mensajes programación reactiva, lo que permitira que un gran número de solicitudes concurrentes se procesen de manera eficiente. Una manera de implementarlo junto a rabbit es la siguiente:
http://projectreactor.io/docs/rabbitmq/snapshot/reference/#_project_reactor
