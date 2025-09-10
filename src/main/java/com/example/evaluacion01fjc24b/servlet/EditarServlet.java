package com.example.evaluacion01fjc24b.servlet;

import com.example.evaluacion01fjc24b.dao.AlumnoDAO;
import com.example.evaluacion01fjc24b.modelo.Alumno;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editar")
public class EditarServlet extends HttpServlet {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Alumno alumno = alumnoDAO.buscarPorId(id);
            req.setAttribute("alumno", alumno);
            RequestDispatcher dispatcher = req.getRequestDispatcher("editar.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error al cargar alumno", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            int edad = Integer.parseInt(req.getParameter("edad"));

            Alumno alumno = new Alumno(id, nombre, email, edad);
            alumnoDAO.actualizar(alumno);

            resp.sendRedirect("listar");
        } catch (Exception e) {
            throw new ServletException("Error al editar alumno", e);
        }
    }
}
