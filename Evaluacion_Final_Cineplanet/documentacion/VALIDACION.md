# Validación técnica de Cineplanet

Fecha: 20/10/2026. Entorno real: JDK 8, GlassFish 4.1.1, SQL Server local.

| N.º | Caso | Resultado obtenido |
|---|---|---|
| 1 | Inyección SQL | Payload enviado al login; acceso denegado con el mensaje genérico. |
| 2 | Fuerza bruta | Al tercer fallo, bloqueo de 5 minutos; persiste al cambiar de sesión e impide la clave correcta durante el bloqueo. |
| 3 | IDOR | Comprobante y búsqueda en Mis entradas rechazan la compra de otro cliente, sin mostrar su detalle. |
| 4 | Escalada de privilegios | CAJERO recibe 403 en los reportes ADMIN y en las JSP directas; tampoco puede usar el pago del cliente. |
| 5 | XSS almacenado | Nombre de cliente guardado en SQL Server; la respuesta contiene el texto escapado y ningún elemento script con el payload. |
| 6 | CSRF | Carrito, pago y anulación rechazan token ausente o incorrecto con 403; anular por GET devuelve 405. |
| 7 | Manipulación de precio | Se enviaron precio y total de S/ 0.01. SQL Server guardó S/ 80.70 usando los precios oficiales de entrada y 2 productos. |
| 8 | Doble venta de butaca | Dos sesiones pagaron simultáneamente la misma butaca: 1 compra confirmada y 1 rechazo; solo se descontó una butaca y se insertó una venta. |
| 9 | Sesión tras logout | La sesión queda invalidada; una petición posterior a Mis entradas redirige al login y conserva Cache-Control: no-store. Botón Atrás pendiente de comprobación visual. |

Compilación Ant y despliegue reales satisfactorios. BD con 12 tablas, 8006 clientes y sin claves de Usuario o Cliente con longitud distinta de 64.
Todas las vistas principales fueron compiladas por GlassFish al solicitarlas por HTTP.
Se comprobó la anulación por POST, su repetición sin segunda devolución y el registro de los ocho tipos de eventos.
Se verificaron las cuatro cabeceras, el 404 propio y el acceso ADMIN a las últimas 100 ventas y a auditoría.
La compra usada para alterar precio se registró por S/ 80.70: una entrada y dos productos con precio de BD.
Se cambió temporalmente el nombre del cliente para probar XSS almacenado y se restauró al terminar.

Pendiente: inspección visual, botón Atrás, capturas reales y Word original del examen completado. El token de sesión, no-store y la recarga de pageshow se encuentran en el código; no se sustituye la prueba visual con una afirmación de cumplimiento.

Estas son comprobaciones de la versión de referencia; no prueban el estado de una maqueta ajena.
