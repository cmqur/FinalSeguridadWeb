# Evaluacion_Final_Cineplanet

Tienda web de entradas y dulcería creada desde cero con el estilo por capas del ejemplo Promart.

## Entorno

NetBeans 8.2, GlassFish 4.1.1, JDK 8, Java EE 7 Web y SQL Server.
El driver mssql-jdbc-13.4.0.jre8.jar está incluido en lib y configurado en Libraries.

## Abrir en NetBeans

1. File > Open Project: seleccionar la carpeta Evaluacion_Final_Cineplanet.
2. En Properties, seleccionar JDK 8 y el servidor GlassFish 4.1.1 de esta PC.
3. Verificar que SQL Server acepte conexiones en localhost:1433.
4. La conexión está en src/java/conexion/ConexionSQL.java:
   jdbc:sqlserver://localhost:1433;databaseName=BD_CineplanetFinal;user=sa;password=123;encrypt=false
5. En esta PC la base ya está creada. Para preparar otra PC, usar el SQL completo de database.
   El script del docente recrea la BD existente; conservar los datos necesarios antes de ejecutarlo.
6. Ejecutar Clean and Build y Run. El nombre del proyecto debe conservarse sin espacios ni tildes.

La aplicación de prueba está desplegada en:
http://localhost:9080/Evaluacion_Final_Cineplanet/LoginServlet
El dominio habitual de NetBeans puede utilizar otro puerto; Run abre la URL de su servidor.

## Cuentas de prueba

- ADMIN: admin / Admin#2026.
- CAJERO: cajero / Cajero#2026.
- CLIENTE: 45678123 / Cliente#2026.

## Flujo

Cliente: login con DNI, cine, función, butacas, dulcería, carrito, pago simulado y comprobante.
Mis entradas permite volver a consultar únicamente sus propias compras.
Personal: panel y anulación por POST. ADMIN además consulta Ventas y Auditoría.
La anulación solicita el número de entrada que aparece en el comprobante.

## Código y seguridad

Paquetes conexion, modelo, dao, controlador, filtro y util.
Consultas con PreparedStatement; hashes SHA-256; sesiones de 15 minutos; bloqueo por tres fallos;
roles; comprobantes por propietario; XSS; CSRF; compra y anulación transaccionales;
precios leídos de SQL Server; control de doble venta; errores propios y auditoría.
Sin Logger, Level, frameworks adicionales ni método mapear.

## Repartición

Consultar REPARTO.md. Cada paso tiene un único responsable.
El paso 7 consiste en ejecutar las pruebas; esta entrega se centra en código y reparto.

## Comprobación de funcionamiento

Proyecto compilado con JDK 8 y Ant y desplegado en GlassFish 4.1.1.
Comprobados por HTTP y SQL: login, pantallas de compra, una entrada más dulcería,
pago con precio oficial, comprobante, Mis entradas, separación de roles, CSRF,
anulación sin duplicar devolución y logout.
El pago es simulado y no realiza cobros reales.
