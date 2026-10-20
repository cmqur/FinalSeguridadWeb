<%@page pageEncoding="UTF-8"%>
<%@include file="WEB-INF/encabezado.jspf"%>
<h1>Panel del personal</h1><p>Sesión de <strong><c:out value="${sessionScope.usuario.nombre}"/></strong> · Rol <c:out value="${sessionScope.usuario.rol}"/></p>
<c:if test="${sessionScope.usuario.rol eq 'ADMIN'}"><div class="acciones"><a class="boton" href="VentaServlet">Ver ventas</a><a class="boton secundario" href="AuditoriaServlet">Ver auditoría</a></div></c:if>
<section><h2>Anular una entrada</h2><p>Busca el número de entrada en el comprobante del cliente. La anulación libera su butaca.</p>
<form class="linea" method="post" action="AnularEntradaServlet"><input type="hidden" name="csrf" value="<c:out value='${sessionScope.csrf}'/>"><label for="idEntrada">Número de entrada</label><input type="number" min="1" name="idEntrada" id="idEntrada" required><button type="submit">Anular entrada</button></form></section>
<%@include file="WEB-INF/pie.jspf"%>
