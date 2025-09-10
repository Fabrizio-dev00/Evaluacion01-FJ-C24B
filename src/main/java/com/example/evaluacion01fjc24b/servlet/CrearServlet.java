package com.example.evaluacion01fjc24b.servlet;

import com.example.evaluacion01fjc24b.dao.AlumnoDAO;
import com.example.evaluacion01fjc24b.modelo.Alumno;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/crear")
public class CrearServlet extends HttpServlet {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            int edad = Integer.parseInt(req.getParameter("edad"));

            Alumno alumno = new Alumno(nombre, email, edad);
            alumnoDAO.insertar(alumno);

            resp.sendRedirect("listar");
        } catch (Exception e) {
            throw new ServletException("Error al crear alumno", e);
        }
    }
}
