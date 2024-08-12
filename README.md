
###########################################################################################
Aplicación de Gestión de Clientes con Spring Boot y MongoDB.
###########################################################################################

DESCRIPCION
	Esta es una aplicación Spring Boot diseñada para gestionar clientes (Customer) con características específicas según el país. 
	Utiliza MongoDB como base de datos y sigue los principios de Clean Architecture, Patrones de diseño y SOLID para garantizar un código limpio 
	y mantenible.

REQUISITOS
	Java 17 o superior
	Spring Boot 3.x
	MongoDB 6.x
	Gradle (para la gestión de dependencias y construcción del proyecto)

DEPENDENCIAS
	spring-boot-starter-data-mongodb
	spring-boot-starter-web
	spring-boot-starter-validation
	spring-boot-starter-test
	junit-platform-launcher
	junit-jupiter-api:5.9.3
	junit-jupiter-engine:5.9.3
	mockito-core:5.5.0
	mockito-junit-jupiter:5.5.0

CONSTRUCCION DEL PROYECTO:
	Actualizar las dependencias via Gradle.

CONFIGURAR MONGODB:
	Asegúrate de que MongoDB está en ejecución en la maquina local (toda la configuracion se encuentra en application.yml).

EJECUTAR LA APLICACION:
	Ir a la clase ClientesApplication y dar "Run" a la clase principal.

USO
	La aplicación expone una API REST para gestionar clientes. Aquí están algunos de los endpoints disponibles:
		GET /customers: Obtener la lista de todos los clientes.
		POST /customers/create: Crear un nuevo cliente.
		GET /customers/{id}: Obtener un cliente específico por ID.

VALIDACIONES
	La aplicación realiza validaciones personalizadas para los atributos de los clientes según el país. 
	Consulta el código para detalles sobre las reglas de validación aplicadas.

PRUEBAS
	Se utilizan pruebas unitarias para garantizar el correcto funcionamiento de la aplicación (Solo a nivel de servicio y repositorio).
	
cURL
Crear clientes: 
	curl --location 'http://localhost:8080/customers/create' \
	--header 'Content-Type: application/json' \
	--data-raw '{
		"nombre": "Braulio Pizarro",
		"email": "braulio.pizarro@example.com",
		"pais": "Chile",
		"ciudades": ["Santiago", "Coquimbo", "Temuco"],
		"fechaRegistro": "2024-08-07",
		"cedula": "17363919-8"
	}
'
Obtener clientes:
	curl --location 'http://localhost:8080/customers'
	
Obtener clientes por id y pais
	curl --location 'http://localhost:8080/customers/buscar?id=66b4325390959106816499d4&pais=Peru'

Obtener clientes por Id:
	curl --location 'http://localhost:8080/customers/66b426379efc4032d9195427'
