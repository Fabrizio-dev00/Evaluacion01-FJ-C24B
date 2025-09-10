<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Alumnos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="mb-0">Lista de Alumnos</h2>
        <!-- 🔙 Botón regresar al menú -->
        <a href="${pageContext.request.contextPath}/index.html" class="btn btn-secondary">
            ⬅️ Regresar al Menú
        </a>
    </div>

    <a href="crear.jsp" class="btn btn-success mb-3">➕ Nuevo Alumno</a>

    <table class="table table-striped table-hover shadow-sm">
        <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Edad</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${listaAlumnos}">
            <tr>
                <td>${a.id}</td>
                <td>${a.nombre}</td>
                <td>${a.email}</td>
                <td>${a.edad}</td>
                <td>
                    <a href="editar?id=${a.id}" class="btn btn-warning btn-sm">✏️ Editar</a>
                    <a href="eliminar?id=${a.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Seguro que quieres eliminar este alumno?');">🗑️ Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
