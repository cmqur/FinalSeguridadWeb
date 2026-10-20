# Condiciones del examen final practico

Curso: Seguridad de Aplicaciones (02414).
Profesor: Mg. David Coragua.
Fecha del examen: 21/10/2026. Duracion: 180 minutos. Calificacion: 20 puntos.

## Desarrollo desde cero

Crear una Web Application en NetBeans 8.2, GlassFish 4.1.1 y JDK 8, con SQL Server.
Nombre: Evaluacion_Final_Cineplanet, sin tildes, ñ ni espacios.
Implementar los siete pasos del Word, con un unico responsable por paso.

## Base y conexion

Ejecutar completo el script docente en SSMS (Ctrl+A y F5), en el entorno inicial del examen.
No modificar la estructura de las tablas. La copia entregada del SQL es identica a la proporcionada.
La BD local de trabajo ya se creo; no volver a ejecutar un script que la elimina si se desea conservarla.

jdbc:sqlserver://localhost:1433;databaseName=BD_CineplanetFinal;user=sa;password=123;encrypt=false

ADMIN: admin / Admin#2026.
CAJERO: cajero / Cajero#2026.
Cliente: su DNI, por ejemplo 45678123 / Cliente#2026.
Las contraseñas se comparan con los hashes SHA-256 guardados en la base.

## Consultas de la aplicacion

Se revisaron los nueve DAO: 25 llamadas a prepareStatement y ninguna llamada a createStatement.
Las consultas tienen texto SQL fijo y los datos se pasan mediante setString, setInt o setBigDecimal.
El uso de Statement.RETURN_GENERATED_KEYS en prepareStatement no crea un Statement comun;
solo pide a JDBC el identificador generado de la venta.
No concatenar valores de usuario, IDs, butacas, precios, filtros ni campos del formulario dentro del SQL.

## Reparto exclusivo

Integrante 1: pasos 1 y 6.
Integrante 2: paso 2.
Integrante 3: pasos 3 y 7.
Integrante 4: paso 4.
Integrante 5: paso 5.

El equilibrio es aproximado. El paso 4 sigue siendo el de mayor alcance funcional al desarrollarlo desde cero.

## Word y evidencias

Cada responsable entrega las capturas de sus pasos 1 a 6 para pegarlas en los recuadros grises del Word.
El integrante 3 completa Resultado obtenido del paso 7 y adjunta una captura real de cada uno de los nueve ataques.
Los resultados HTTP y SQL previos estan documentados, pero no reemplazan las capturas del navegador.
Sigue pendiente comprobar visualmente el boton Atras despues del logout y completar las capturas.

## Organizacion orientativa de los 180 minutos

0 a 15: base, proyecto, paquetes y contratos de sesion y DAO.
15 a 90: cada integrante desarrolla sus pasos; el 3 prepara tambien el registro de pruebas.
90 a 130: integrar los componentes; cada responsable corrige sus propios pasos.
130 a 165: el integrante 3 ejecuta las nueve pruebas y obtiene evidencias; los demas corrigen defectos de sus pasos.
165 a 180: incorporar capturas, completar integrantes.txt, declarar IA y comprobar el ZIP final.
Este cronograma es orientativo; no demuestra que todas las partes requieran el mismo tiempo.
