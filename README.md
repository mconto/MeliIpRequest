# MeliIpRequest
Este proyecto es la prueba de desarrollo para participar en proceso de selección con Mercado Libre.

El comando de empaquetado del aplicativo es el siguiente:

# mvnw clean package -Dspring.profiles.active=dev

El comando de creación de la imagen docker es el siguiente:

# docker build -t meli-app .

El comando de ejecución de la imagen docker es el siguiente:

# docker run -p 8081:8081 meli-app 201.244.109.55 BUSCAR
La palabra "BUSCAR" puede cambiar por "PAGAR" o "COMPRAR" según sea la necesidad.
Las variables de entorno se encuentran configuradas en el Dockerfile para este escenario.

-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!

Para la ejecución desde maven el comando es el siguiente:

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DAPI_EXCHANGE_ACCESS_KEY=ede902f3a4fb76bf9465b5692b823c7a -DAPI_EXCHANGE_URL=http://data.fixer.io/api/latest -DAPI_LOCALIZATOR_ACCESS_KEY=33cb4cbcb239a0eae8bc72a9c57381b7 -DAPI_LOCALIZATOR_URL=http://api.ipapi.com/api/ -DBASE_DE_DATOS_URL=jdbc:sqlserver://DESKTOP-M01IACH\\SQLEXPRESS;databaseName=meliDb;TrustServerCertificate=True -DBASE_DE_DATOS_USUARIO=sa -DBASE_DE_DATOS_PASS=egallery" -Dspring-boot.run.arguments="201.244.109.55 BUSCAR"

Consta de dos parámetros de entrada que son la IP y la estrategia de ejecución según el patrón "Strategy". Las opciones son "BUSCAR", "COMPRAR", "PAGAR"; considerando que el microservicio se utiliza en los tres escenarios y puede personalizarse la estrategia a cada una de las opciones.

Los servicios para obtener las distancas están expuestos en servicios REST mediante un controlador y consumen la base de datos del aplicativo construida en SQLServer. Se adjunta la instrucción SQL para crear la tabla.

La IP introducida está localizada en Bogotá, el programa se corre desde linea de comandos y el argumento puede variar.

Inicialmente se valida que la IP cumpla con un formato válido.

Posteriormente se consumen las API's para obtener la información relevante y construir la respuesta esperada.

Existen unos endpoints expuestos a consumo desde un cliente como Postman (para efectos de prueba) con los que se pueden 
obtener los datos de distancia más lejana, más cercana y promedio.

Se realizaron dos tipos diferentes de mapeo de data a partir de un servicio a un consumo externo.

Se implementaron logs y control de excepciones.

Implementación de patrones de diseño y principios SOLID.


CREATE TABLE distancias (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    pais VARCHAR(255) NOT NULL,
    distancia FLOAT NOT NULL,
    invocaciones INT DEFAULT 0,
    version INT,
    CONSTRAINT CHK_Distancias_Distancia CHECK (distancia >= 0)
);
