# Reto9
Hecho por: Samuel Acevedo(CC.1001016099) Efraín García(CC.1001370984)

Respuestas:

Punto 2) Lea el archivo de los precios cada 5 líneas (días) (usando el acceso aleatorio) hasta su fin, y escriba en otro archivo de manera secuencial las mismas salidas que se especificaron para el punto anterior.

Este punto no puede ser realizado usando el acceso aleatorio puesto que para ello cada línea debería estar compuesta por el mismo número de bytes, en este caso se presentan ocaciones donde algunas lineas son ligeramente más largas que otras debido al dato volumen de transacciones, esta situación podría solucionarse si en lugar de usar el acceso aleatorio se emplease un stream y el metodo skip.

Punto 3.

El metodo de leer cada linea es útil para tener una idea más precisa y obtener datos menos cesgados al realizar un análisis estadístico, por otro lado el metodo de leer cada 5 lineas es útil a la hora de visualizar los resultados de una manera más general, además de ahorrar recursos a la maquina puesto que se evita tener que leer al completo el archivo.
