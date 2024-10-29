# MeliIpRequest
Este proyecto es la prueba de desarrollo para participar en proceso de selección con Mercado Libre.

El comando de ejecución del aplicativo desde escritorio es el siguiente:

# mvn spring-boot:run -D"spring-boot.run.arguments=8.242.150.100"

La IP introducida está localizada en Bogotá, el programa se corre desde linea de comandos y el argumento puede variar.

Inicialmente se valida que la IP cumpla con un formato válido.

Posteriormente se consumen las API's para obtener la información relevante y construir la respuesta esperada.

Existen unos endpoints expuestos a consumo desde un cliente como Postman (para efectos de prueba) con los que se pueden 
obtener los datos de distancia más lejana, más cercana y promedio.

Se realizaron dos tipos diferentes de mapeo de data a partir de un servicio a un consumo externo.

Se implementaron logs y control de excepciones.

Implementación de patrones de diseño y principios SOLID.
