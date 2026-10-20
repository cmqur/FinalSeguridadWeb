<%@page pageEncoding="UTF-8"%>
<%@include file="WEB-INF/encabezado.jspf"%>
<p class="etiqueta"><c:out value="${funcion.sede}"/> · Sala <c:out value="${funcion.sala}"/></p><h1><c:out value="${funcion.titulo}"/></h1><p><c:out value="${funcion.fechaHora}"/> · S/ <c:out value="${funcion.precio}"/> por entrada. Máximo 10 entradas por compra.</p>
<form method="post" action="CarritoServlet"><input type="hidden" name="csrf" value="<c:out value='${sessionScope.csrf}'/>"><input type="hidden" name="accion" value="entrada"><input type="hidden" name="idFuncion" value="<c:out value='${funcion.idFuncion}'/>">
<div class="sala"><div class="pantalla">PANTALLA</div><c:forEach var="fila" items="${filas}"><div class="fila"><c:forEach var="b" items="${fila}"><label class="butaca"><input type="checkbox" name="butaca" value="<c:out value='${b}'/>" <c:if test="${ocupadas.contains(b)}">disabled</c:if>><span><c:out value="${b}"/></span></label></c:forEach></div></c:forEach></div>
<p>Azul: disponible · Gris: ocupada o ya agregada al carrito · Rojo: seleccionada.</p><button type="submit">Agregar butacas al carrito</button> <a href="TiendaServlet">Volver a la cartelera</a></form>
<%@include file="WEB-INF/pie.jspf"%>
