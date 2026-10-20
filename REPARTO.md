# Repartición de Cineplanet entre cinco integrantes

El proyecto se desarrolla desde cero. Cada paso completo tiene un único responsable.

| Integrante | Pasos exclusivos | Trabajo |
|---|---|---|
| 1 | 1 y 6 | Proyecto NetBeans, BD y conexión; errores, cabeceras y auditoría. |
| 2 | 2 | Autenticación, hashes, bloqueo, sesión nueva, expiración y logout. |
| 3 | 3 y 7 | Roles, propiedad de las compras y todas las pruebas de funcionamiento y seguridad. |
| 4 | 4 | Cartelera, butacas, dulcería, carrito, transacción de compra y comprobante. |
| 5 | 5 | Escape XSS, token CSRF, validación de POST y anulación transaccional. |

El reparto es aproximado por alcance. El paso 4 concentra más desarrollo funcional y se mantiene
completo en una persona para respetar la condición de no compartir pasos. Los puntos de la rúbrica
no son porcentajes de aporte ni equivalen al tiempo de programación.

## Integrante 1

- conexion/ConexionSQL.java.
- modelo/Auditoria.java y dao/AuditoriaDAO.java.
- controlador/AuditoriaServlet.java y filtro/CabecerasFilter.java.
- web/error.jsp, web/auditoria.jsp y web/WEB-INF/web.xml.
- Configuración NetBeans, driver y comprobación de la base del docente.

## Integrante 2

- modelo/Usuario.java y Cliente.java.
- dao/UsuarioDAO.java y ClienteDAO.java.
- util/Seguridad.java; controlador/LoginServlet.java y LogoutServlet.java.
- web/login.jsp y panel.jsp.
- Bloqueo de cinco minutos tras tres fallos y sesión de quince minutos.

## Integrante 3

- filtro/SesionFilter.java y dao/ConsultaVentaDAO.java.
- controlador/ComprobanteServlet.java, MisEntradasServlet.java y VentaServlet.java.
- web/misEntradas.jsp y ventas.jsp.
- Ejecutar los nueve casos del paso 7. Los fallos de otros pasos los corrige su responsable.

## Integrante 4

- Modelos Sede, Funcion, Producto, Entrada, DetalleVenta, Venta, Carrito, ItemEntrada e ItemProducto.
- dao/SedeDAO.java, FuncionDAO.java, ProductoDAO.java y VentaDAO.java.
- controlador/TiendaServlet.java, ButacasServlet.java, DulceriaServlet.java,
  CarritoServlet.java y PagoServlet.java.
- web/tienda.jsp, butacas.jsp, dulceria.jsp, carrito.jsp, pago.jsp y comprobante.jsp.
- Precios de BD, claves generadas, disponibilidad, commit, rollback y conflicto de butacas.

## Integrante 5

- util/Csrf.java, dao/EntradaDAO.java y controlador/AnularEntradaServlet.java.
- Escape con c:out en todas las vistas y los fragmentos compartidos.
- Campos csrf y validación del token en todos los formularios y POST que corresponden.
- Anulación por POST, devolución de una butaca y auditoría en la misma transacción.

## Integración

El integrante 5 incorpora los controles de su paso en los archivos que los necesitan.
Eso no divide los pasos 2 y 4 entre dos responsables. El integrante 3 prueba todo el sistema
como parte del paso 7 y comunica los defectos al dueño de cada paso.
No se requieren respuestas al Word ni capturas para esta entrega de código y reparto.
