<%@page pageEncoding="UTF-8"%>
<%@include file="WEB-INF/encabezado.jspf"%><h1>Mis entradas y compras</h1><form class="linea" method="get" action="MisEntradasServlet"><label for="id">Número de compra</label><input type="number" name="id" id="id" min="1" required><button type="submit">Buscar mi compra</button></form>
<%@include file="WEB-INF/detalleComprobante.jspf"%>
<div class="tabla"><table><thead><tr><th>Compra</th><th>Cine</th><th>Fecha</th><th>Total</th><th>Estado</th><th></th></tr></thead><tbody><c:forEach var="v" items="${ventas}"><tr><td><c:out value="${v.idVenta}"/></td><td><c:out value="${v.sede}"/></td><td><c:out value="${v.fechaVenta}"/></td><td>S/ <c:out value="${v.total}"/></td><td><c:out value="${v.estado}"/></td><td><a href="ComprobanteServlet?id=<c:out value='${v.idVenta}'/>">Ver comprobante</a></td></tr></c:forEach></tbody></table></div>
<%@include file="WEB-INF/pie.jspf"%>
