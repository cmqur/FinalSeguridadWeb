<%@page pageEncoding="UTF-8"%>
<%@include file="WEB-INF/encabezado.jspf"%>
<section class="acceso"><p class="etiqueta">Tu próxima película empieza aquí</p><h1>Bienvenido a Cineplanet</h1><p>Ingresa para elegir tus butacas y preparar tu compra.</p>
<form method="post" action="LoginServlet"><input type="hidden" name="csrf" value="<c:out value='${sessionScope.csrf}'/>">
<label for="tipo">Tipo de acceso</label><select id="tipo" name="tipo"><option value="cliente">Cliente</option><option value="personal">Personal del cine</option></select>
<label for="cuenta">DNI o usuario del personal</label><input id="cuenta" name="cuenta" maxlength="30" autocomplete="username" required>
<label for="clave">Contraseña</label><input id="clave" name="clave" type="password" maxlength="200" autocomplete="current-password" required>
<button type="submit">Iniciar sesión</button></form></section>
<%@include file="WEB-INF/pie.jspf"%>
