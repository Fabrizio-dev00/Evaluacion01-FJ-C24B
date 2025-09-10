package com.example.evaluacion01fjc24b.servlet;

import com.example.evaluacion01fjc24b.dao.AlumnoDAO;
import com.example.evaluacion01fjc24b.modelo.Alumno;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listar")
public class ListarServlet extends HttpServlet {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // Obtener lista de alumnos desde DAO
            List<Alumno> lista = alumnoDAO.listar();

            // Pasar lista a la vista (JSP)
            req.setAttribute("listaAlumnos", lista);

            // Redirigir a listar.jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("listar.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error al listar alumnos", e);
        }
    }
}
