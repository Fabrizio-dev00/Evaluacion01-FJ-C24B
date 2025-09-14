<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.evaluacion01fjc24b.modelo.Alumno" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Alumno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<h2>Editar Alumno</h2>
<form action="editar" method="post" class="card p-4 shadow rounded">
    <input type="hidden" name="id" value="${alumno.id}">

    <div class="mb-3">
        <label class="form-label">Nombre:</label>
        <input type="text" name="nombre" value="${alumno.nombre}" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Email:</label>
        <input type="email" name="email" value="${alumno.email}" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Edad:</label>
        <input type="number" name="edad" value="${alumno.edad}" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Curso:</label>
        <select name="curso_id" class="form-select" required>
            <c:forEach var="c" items="${listaCursos}">
                <option value="${c.id}" <c:if test="${c.id == alumno.curso.id}">selected</c:if>>
                        ${c.nombre}
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-success">Actualizar</button>
    <a href="listar" class="btn btn-secondary">Cancelar</a>
</form>

</body>
</html>