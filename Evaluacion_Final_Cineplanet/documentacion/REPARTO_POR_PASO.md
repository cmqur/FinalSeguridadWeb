# Reparto para crear Cineplanet desde cero

| Integrante | Pasos exclusivos | Alcance | Puntos | Fuentes Java propias |
|---|---|---|---|---|
| 1 | 1 y 6 | Proyecto y BD; errores, cabeceras y auditoría | 4,5 | 5 |
| 2 | 2 | Autenticación y sesiones | 3,5 | 7 |
| 3 | 3 y 7 | Control de acceso y las nueve pruebas | 5 | 5 |
| 4 | 4 | Tienda, butacas, carrito, pago y comprobante | 4 | 18 |
| 5 | 5 | XSS, CSRF y anulación por POST | 3 | 3 |

Cada paso del 1 al 7 aparece una sola vez. Los 38 archivos Java son inventario, no una medida de esfuerzo. El paso 5 además incorpora controles en vistas y controladores de otros pasos. El paso 7 agrega pruebas y evidencias.

El paso 4 sigue siendo el de mayor alcance funcional; no se declara un reparto exactamente igual en tiempo.

## Integrante 1 Proyecto y auditoría
Pasos 1 y 6 completos

- Crear una Web Application en NetBeans 8.2 con JDK 8, GlassFish 4.1.1 y Java EE 7 Web. Agregar el driver e implementar ConexionSQL.
- Verificar las 12 tablas, hashes de 64 caracteres y datos del SQL completo. Conservar el esquema del docente.
- Implementar las páginas 404 y 500, el manejo de Exception, las cuatro cabeceras y el registro de los ocho eventos.
- Entregar las capturas de NetBeans, hashes, error propio, cabeceras y últimos 100 eventos visibles solo para ADMIN.

Archivos: conexion/ConexionSQL.java; modelo/Auditoria.java; dao/AuditoriaDAO.java; controlador/AuditoriaServlet.java; filtro/CabecerasFilter.java; web/error.jsp; web/auditoria.jsp; web/WEB-INF/web.xml y configuración del proyecto.

## Integrante 2 Autenticación y sesiones
Paso 2 completo

- Implementar SHA-256 en UTF-8 y la comparación parametrizada de UsuarioDAO y ClienteDAO. Obtener el rol desde la BD.
- Implementar y comprobar mensaje genérico, tres fallos, bloqueo de cinco minutos y contador por cuenta compartido entre peticiones.
- Implementar la invalidación de la sesión anterior, un nuevo identificador, 15 minutos de inactividad y el logout.
- Entregar capturas del login fallido, bloqueo y sesión correcta. Los eventos se guardan a través de AuditoriaDAO.

Archivos: modelo/Usuario.java y Cliente.java; dao/UsuarioDAO.java y ClienteDAO.java; util/Seguridad.java; controlador/LoginServlet.java y LogoutServlet.java; web/login.jsp y panel.jsp.

## Integrante 3 Permisos y pruebas de seguridad
Pasos 3 y 7 completos

- Crear SesionFilter y proteger también las JSP directas. Separar CLIENTE y personal y exigir ADMIN en Ventas y Auditoría.
- Comprobar idVenta e idCliente de la sesión en el comprobante y en la búsqueda de Mis entradas. Verificar no-store.
- Ejecutar y documentar las nueve pruebas del paso 7. Incluir una captura real por prueba y completar el Resultado obtenido en el Word del examen.
- Informar los defectos al dueño del paso correspondiente; este corrige su paso. Ejecutar una prueba sobre una función no comparte su autoría.

Archivos: filtro/SesionFilter.java; dao/ConsultaVentaDAO.java; controlador/ComprobanteServlet.java, MisEntradasServlet.java y VentaServlet.java; web/misEntradas.jsp y ventas.jsp; tabla y evidencias del paso 7.

## Integrante 4 Tienda y compra completa
Paso 4 completo

- Crear la cartelera por cine de los próximos siete días, dulcería y mapa A a J con butacas 1 a 12, respetando capacidad y ocupación.
- Crear el carrito en sesión. Validar formato, ocupación, duplicados, máximo de diez entradas y cantidades de uno a diez en servidor.
- Implementar VentaDAO.registrar con RETURN_GENERATED_KEYS, precios de SQL Server y una misma conexión para toda la transacción.
- Implementar disponibilidad condicionada, rollback ante conflicto y comprobante. Respetar el índice de butaca activa y limpiar el carrito después del commit.

Archivos: Modelos de catálogo, carrito y venta; dao/SedeDAO.java, FuncionDAO.java, ProductoDAO.java y VentaDAO.java; controlador/TiendaServlet.java, ButacasServlet.java, DulceriaServlet.java, CarritoServlet.java y PagoServlet.java; vistas de tienda, butacas, dulcería, carrito, pago y comprobante.

## Integrante 5 XSS CSRF y anulación
Paso 5 completo

- Aplicar c:out a los datos y atributos de las vistas: nombre, auditoría, mensajes, campos y comprobantes.
- Generar el token aleatorio y renovarlo al entrar. Incorporar campos ocultos y validación en todos los POST.
- Comprobar rechazo 403 y evento CSRF con token ausente o incorrecto. La anulación solo se permite por POST al personal autorizado.
- Implementar EntradaDAO.anular: cambio condicional de estado, devolución de una butaca y ANULACION en una transacción. Impedir la devolución repetida.

Archivos: util/Csrf.java; dao/EntradaDAO.java; controlador/AnularEntradaServlet.java; campos csrf, escape y validación del paso 5 en vistas y controladores existentes. La revisión de las nueve pruebas pertenece al integrante 3.

Plan vigente: Cineplanet_Plan_Creacion_Desde_Cero_5_Integrantes.docx. Examen del 21/10/2026, 180 minutos y 20 puntos.
