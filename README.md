# Lucaje
Se trata sobre una tienda de renta de herramientas en la cual se administran usuarios y proveedores mirando las herramientas disponibles y su historial de reservas

## Tecnologias usadas
[![My Skills](https://skillicons.dev/icons?i=react,html,css,spring,vite,postgresql)](https://skillicons.dev)

## Diagrama de la base de datos 

![630361b1-73e2-4432-ae4b-ac400dcf1e3b](https://github.com/user-attachments/assets/fe817445-15e2-42b5-9756-bff5ee912e30)

## Estructura
Este proyecto esta dividido en dos repositorios este que es el backend y para acceder al frontend accede al siguinete link: https://github.com/JaynAP1/Front-project

## Uso
Para hacer uso del proyecto es necesario tener dos repositorios en nuestro equipo.

```bash
    #Este es el link para clonar el backend del proyecto.
    git clone https://github.com/JaynAP1/Java_Springboot_project.git
    #Este es el link del frontend del proyecto.
    git clone https://github.com/JaynAP1/React_fronted_project.git
```

Una vez los dos repositorios han sido descargados en nuestra computadora es importante mencionar la importancia de tener postgress intalado en nuestra maquina y recomendamos encarecidamente el uso de pgAdmin4 como gestor.

Es muy importante aclarar que la configuracion del archivo application.properties cambiara dependiendo de la configuracion propia de postgres.
De ser necesario cambiar la configuracion.

Una vez abrimos el pgAdmin crearemos un nuevo server y colocaremos en el nombre el que queramos y en el panel connection importante, escribir los parametros necesaros para poder acceder a ella. Este paso no es totalmente necesario.

Despues de esto, empezaremos dirigiendonos al archivo Taller1Application e iniciaremos el programa. Mientras el springboot inicializa sera importante dirigirnos hacia la carpeta del proyecto React_front_project y hacer los siguientes comandos.

```bash
    #Instalara todas las dependencias necesarias para ejecutar el frontend del proyecto.
    npm install

    #Luego ejecutaremos.
    npm run dev
```
Una vez esos dos comandos han sido inicializados nos dejaran una url a la que podremos acceder para comenzar a usar el proyecto libremente.


## Desarrollado por.

Jerxon Jair Correa Amaris, Luis Henao Bermon, Camilo Machuca. 


