<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.evaluacion01fjc24b.modelo.Alumno" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Alumno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%
    Alumno alumno = (Alumno) request.getAttribute("alumno");
%>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-header bg-warning text-dark">
            <h3 class="mb-0">✏️ Editar Alumno</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/editar" method="post">
                <input type="hidden" name="id" value="<%= alumno.getId() %>">

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%= alumno.getNombre() %>" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electrónico:</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%= alumno.getEmail() %>" required>
                </div>
                <div class="mb-3">
                    <label for="edad" class="form-label">Edad:</label>
                    <input type="number" class="form-control" id="edad" name="edad" value="<%= alumno.getEdad() %>" required>
                </div>
                <button type="submit" class="btn btn-warning">Actualizar</button>
                <a href="${pageContext.request.contextPath}/listar" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
