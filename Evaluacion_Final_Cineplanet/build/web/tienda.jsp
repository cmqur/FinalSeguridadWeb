<%@page pageEncoding="UTF-8"%>
<%@include file="WEB-INF/encabezado.jspf"%>
<p class="etiqueta">En pantalla</p><h1>Elige tu próxima función</h1><p>Cartelera disponible para los próximos siete días.</p>
<form class="linea" method="get" action="TiendaServlet"><label for="idSede">Tu cine</label><select name="idSede" id="idSede"><c:forEach var="s" items="${sedes}"><option value="<c:out value='${s.idSede}'/>" <c:if test="${s.idSede == idSede}">selected</c:if>><c:out value="${s.nombre}"/> · <c:out value="${s.ciudad}"/></option></c:forEach></select><button type="submit">Ver cartelera</button></form>
<div class="tabla"><table><thead><tr><th>Película</th><th>Fecha y hora</th><th>Sala</th><th>Precio</th><th>Disponibles</th><th></th></tr></thead><tbody>
<c:forEach var="f" items="${funciones}"><tr><td><strong><c:out value="${f.titulo}"/></strong></td><td><c:out value="${f.fechaHora}"/></td><td><c:out value="${f.sala}"/></td><td>S/ <c:out value="${f.precio}"/></td><td><c:out value="${f.butacasDisponibles}"/></td><td><a class="boton" href="ButacasServlet?idFuncion=<c:out value='${f.idFuncion}'/>">Elegir butacas</a></td></tr></c:forEach>
<c:if test="${empty funciones}"><tr><td colspan="6">No hay funciones disponibles para este cine en los próximos siete días.</td></tr></c:if></tbody></table></div>
<section><h2>Completa tu película con dulcería</h2><%@include file="WEB-INF/productos.jspf"%></section>
<%@include file="WEB-INF/pie.jspf"%>
