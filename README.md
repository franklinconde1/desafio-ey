# desafio-ey
Evaluación Java

### Características
Desarrollo de micreo-servicio que expone una API RESTful para creación de usuarios.
- Formato response y request JSON.
- Spring Boot version: 2.5.5
- Gradle version 8.4
- Java 1.8
- Base de dato en memoria H2.
- Test de integración con Junit y Mokito
- Hibernate ORM core version 5.4.32.Final
- Hibernate Commons Annotations 5.1.2.Final
- Hibernate: org.hibernate.dialect.H2Dialect
- o.s.b.w.embedded.tomcat.TomcatWebServer: Tomcat initialized with port(s)

###Registro de usuario
El endpoint recibe un usuario con los campos "nombre", "correo", "contraseña",
más un listado de objetos "teléfono", respetando el siguiente formato:
#####Json
```json
	{
"name" : "Juan Rodriguez" ,
"email" : " juan@rodriguez.org " ,
"password" : "hunter2" ,
"phones" : [
			{
			"number" : "1234567" ,
			"citycode" : "1" ,
			"contrycode" : "57"
			}
				]
	}
```
Los Mensajes de respuestas tienen el siguiente formato:
#####Json
```json
	{
    "mensaje": "mensaje de respuesta"
	}
```
### Requerimientos para el response
- Responder el código de status HTTP adecuado
- En caso de éxito, retorne el usuario y los siguientes campos:
	- id : id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID)
	- created : fecha de creación del usuario
	- modified : fecha de la última actualización de usuario
	- Si el email existe en la base de datos, deberá retornar un error "El correo ya está registrado".
	- El correo debe seguir una expresión regular para validar que formato sea el correcto (aaaaaaa@dominio.cl).
	- El password debe seguir una expresión regular para validar que formato sea el correcto. (Una letra Mayúscula, letras minúsculas, y dos numeros)

### Para probar el proyecto usted debe
###Correr el proyecto desde la clase Main UsersApplication
#####Deberia levantar en el Tomcat started on port(s): 8080 (http) with context path, como se muestra a continuación.
[![Run Project](https://parzibyte.me/blog/wp-content/uploads/2019/09/Ejecutando-App-de-Spring-Boot-en-la-terminal-de-IntelliJ-IDEA.png "Run Project")](https://parzibyte.me/blog/wp-content/uploads/2019/09/Ejecutando-App-de-Spring-Boot-en-la-terminal-de-IntelliJ-IDEA.png "Run Project")

###Para probar los endpoints debe utilizar la herramienta Postman con las siguientes URL:
#####POST createUser: http://localhost:8080/api/users/register que debería devolver un response de la siguiente forma:
```json
{
    "id": "4e67a79a-c9c2-4a29-9501-cd7372b66890",
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter21",
    "createdAt": "2023-11-28T20:48:41.067",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```
#####GET getAllUser: http://localhost:8080/api/users/all que debería devolver un response de la siguiente forma:
```json
[
    {
        "id": "bfb702f1-da47-43d3-9342-fc48004676d4",
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "Hunter21",
        "createdAt": "2023-11-28T20:13:24.107",
        "phones": [
            {
                "id": 1,
                "number": "1234567",
                "citycode": "1",
                "countrycode": "57"
            }
        ]
    },
    {
        "id": "7cb24fc5-9c5c-430b-933a-48eac900a910",
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.cl",
        "password": "Hunter21",
        "createdAt": "2023-11-28T20:14:49.651",
        "phones": [
            {
                "id": 2,
                "number": "1234567",
                "citycode": "1",
                "countrycode": "57"
            }
        ]
    }
]
```
#####GET getUserByEmail: http://localhost:8080/api/users/email  el cual requiere un params (Por ejemplo: key=email y value=juan@rodriguez.org) y que debería devolver un response de la siguiente forma:
```json
{
    "id": "bfb702f1-da47-43d3-9342-fc48004676d4",
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter21",
    "createdAt": "2023-11-28T20:13:24.107",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```
#####PATCH updateUser: http://localhost:8080/api/users/update que requiere un body
```json
{
"name": "Juan Alberto Rodriguez Soto",
"email": "juan@rodriguez.org",
"password": "Hunter21",
"phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "countrycode": "57"
        }
    ]
}
```
### y que debería devolver un response de la siguiente forma:
```json
{
    "id": "bfb702f1-da47-43d3-9342-fc48004676d4",
    "name": "Juan Alberto Rodriguez Soto",
    "email": "juan@rodriguez.org",
    "password": "Hunter21",
    "createdAt": "2023-11-28T20:15:11.728",
    "phones": [
        {
            "id": 3,
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

###End
