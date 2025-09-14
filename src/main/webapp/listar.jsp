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

<div class="container mt-4">
    <h2 class="text-center mb-4">📋 Lista de Alumnos</h2>

    <div class="text-end mb-3">
        <a href="crear" class="btn btn-success">➕ Nuevo Alumno</a>
    </div>

    <table class="table table-hover table-bordered align-middle">
        <thead class="table-dark text-center">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Edad</th>
            <th>Curso</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${listaAlumnos}">
            <tr>
                <td class="text-center">${a.id}</td>
                <td>${a.nombre}</td>
                <td>${a.email}</td>
                <td class="text-center">${a.edad}</td>
                <td class="text-center">
                    <span class="badge bg-info text-dark">${a.curso.nombre}</span>
                </td>
                <td class="text-center">
                    <a href="editar?id=${a.id}" class="btn btn-warning btn-sm">✏️ Editar</a>
                    <a href="eliminar?id=${a.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Seguro que quieres eliminar este alumno?')">🗑 Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a href="index.html" class="btn btn-secondary">⬅️ Volver al menú</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
