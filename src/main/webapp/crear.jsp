<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Alumno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<h2 class="mb-4">Registrar Alumno</h2>

<form action="crear" method="post" class="card p-4 shadow rounded">
    <div class="mb-3">
        <label class="form-label">Nombre:</label>
        <input type="text" name="nombre" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Email:</label>
        <input type="email" name="email" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Edad:</label>
        <input type="number" name="edad" class="form-control" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Curso:</label>
        <select name="curso_id" class="form-select" required>
            <option value="">-- Selecciona un curso --</option>
            <c:forEach var="c" items="${listaCursos}">
                <option value="${c.id}">${c.nombre}</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-primary w-100 mb-2">Guardar</button>
    <a href="listar" class="btn btn-secondary w-100">Cancelar</a>
</form>

</body>
</html>
