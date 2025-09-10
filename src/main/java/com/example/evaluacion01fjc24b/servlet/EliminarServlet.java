package com.example.evaluacion01fjc24b.servlet;

import com.example.evaluacion01fjc24b.dao.AlumnoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/eliminar")
public class EliminarServlet extends HttpServlet {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            alumnoDAO.eliminar(id);
            resp.sendRedirect("listar");
        } catch (Exception e) {
            throw new ServletException("Error al eliminar alumno", e);
        }
    }
}
