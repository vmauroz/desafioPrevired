# DESAFIO
Aplicación Java para leer un archivo CSV con información de empleados, validar los datos y calcular el salario final de cada uno, considerando reglas de negocio y bonificaciones por antigüedad.

***
## Requisitos de Negocio

### Validaciones

1. RUT único: No se permiten duplicados
2. Salario base: Debe ser igual o mayor a $400.000.-
3. Bonos: No pueden superar el 50% del salario base
4. Descuentos: No pueden superar el salario base
5. Fecha de ingreso:
	- formato válido (yyyy-MM-dd)
	- Anterior a hoy

### - Cálculos requeridos

1. Antigüedad: cantidad de años desde fecha de ingreso hasta hoy.
2. Bono antiguedad (se suma a los bonos). Porcentaje sobre sueldo base:

	- Mayor 5 años: +10%
	- Entre 3 y 5 años: +5%
	- Menos 3 años: 0%
	
3. Salario final: SalarioBase + Bonos + BonificaciónAntigüedad - Descuentos

### - Salida esperada
1. Un nuevo archivo CSV con los empleados válidos y su salario final.
2. Un archivo separado con los registros no válidos y el motivo.
3. Mostrar por consola: 

	- Total de empleados válidos e inválidos
	- Promedio de salario final
	- Antigüedad promedio
	
4. La salida en CSV será dentro la carpeta del proyecto en la subcarpeta **“src/main/resources/output/”**

### Requisitos técnicos

- Generar proyecto Maven con Java 8 o superior.
- Manejo de archivos con _**BufferedReader / FileReader**_.
- Uso de clases como **_LocalDate, Period, DateTimeFormatter_**.
- Validación y manejo de errores con mensajes claros.
- Código limpio, modular y bien documentado.
- Archivo **_README.md_** con información del proyecto e instrucciones de
  instalación
- Se entrega un archivo CSV llamado **"empleados.csv"**. Almacenarlo en la carpeta **“src/main/resources/input/empleados.csv”** dentro de la carpeta del proyecto o  instalación. 

## Instalación y Ejecución

***

### Requisitos técnicos

- Java 8 o superior (Desarrollado en Java 17)
- Configurar las variables de entorno **"JAVA_HOME"**
- Agregar al PATH del sistema operativo la ruta a la **carpeta "./bin"** 
  de la carpeta de instalación de Java
- Maven 

### Compilar la aplicación con Maven: 

1. Abrir un consola de línea de comandos del SO 
2. Ir a la carpeta raiz del proyecto **"desafioPrevired"** (_verificar   que exista pom.xml_)
3. Ejecutar el comando: **mvn clean install** (genera un archivo _"desafioPrevired-0.0.1.jar"_)
4. Copiar el archivo "desafioPrevired-0.0.1.jar" a una **carpeta de despliegue** (ejemplo "/desfio").
5. Dentro de **carpeta de despliegue** y crear las subcarpetas:
	- src/main/resources/input
	- src/main/resources/output
6. En la carpeta **"./src/main/resources/input"** copiar el archivo **"empleados.csv"**

### Ejecutar la aplicación

1. Abrir un consola de línea de comandos del sistema operativo
2. Ir a la **carpeta de despliegue** (ejemplo "/desfio")
3. Ejecutar la aplicación con el siguiente comando: **java -jar desafioPrevired-0.0.1.jar**

### Generación de Salidas
La aplicación genera 2 archivos de salida con la información extraída del archivo de entrada:

1. empleadosValidos.csv: con los registros válidos de cada empleado y el correspondiente cálculo de salarios. Incluye los campos:	**Nombre, Apellido, RUT, Cargo, SalarioBase, Bonos, BonoAntiguedad, Descuentos, SalarioFinal, FechaIngreso**
2. empleadosNoValidos.csv: con los registros NO válidos y el motivo. Incluye los campos: **Nombre, Apellido, RUT, Cargo, SalarioBase, Bonos, Descuentos, FechaIngreso, MotivoError**
3. Salida por consola: Genera la salida por consola de la siguiente información estadística: Total registros empleados válidos, Promedio de Salario final, Antiguedad Promedio de los empleados, Total registros de empleados NO válidos