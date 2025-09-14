package com.example.evaluacion01fjc24b.servlet;

import com.example.evaluacion01fjc24b.dao.AlumnoDAO;
import com.example.evaluacion01fjc24b.dao.CursoDAO;
import com.example.evaluacion01fjc24b.modelo.Alumno;
import com.example.evaluacion01fjc24b.modelo.Curso;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/editar")
public class EditarServlet extends HttpServlet {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Alumno alumno = alumnoDAO.buscarPorId(id);
            List<Curso> cursos = cursoDAO.listar();

            req.setAttribute("alumno", alumno);
            req.setAttribute("listaCursos", cursos);

            RequestDispatcher dispatcher = req.getRequestDispatcher("editar.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error al cargar alumno para edición", e);
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
            int cursoId = Integer.parseInt(req.getParameter("curso_id"));

            Alumno alumno = new Alumno(id, nombre, email, edad);
            alumno.setCurso(new Curso(cursoId, null, null)); // solo guardamos el ID

            alumnoDAO.actualizar(alumno);

            resp.sendRedirect("listar");
        } catch (Exception e) {
            throw new ServletException("Error al editar alumno", e);
        }
    }
}
