<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="cssError" value="/recursos/estilos.css"/><c:url var="inicioError" value="/LoginServlet"/>
<!DOCTYPE html><html lang="es"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Cineplanet</title><link rel="stylesheet" href="<c:out value='${cssError}'/>"></head><body><header><span class="marca">cineplanet</span></header><main><h1>No pudimos completar la solicitud</h1><p>La página no está disponible o no tienes permiso para acceder. Vuelve al inicio e inténtalo nuevamente.</p><a class="boton" href="<c:out value='${inicioError}'/>">Volver al inicio</a></main></body></html>
