package com.example.evaluacion01fjc24b.servlet;

import com.example.evaluacion01fjc24b.dao.AlumnoDAO;
import com.example.evaluacion01fjc24b.dao.CursoDAO;
import com.example.evaluacion01fjc24b.modelo.Alumno;
import com.example.evaluacion01fjc24b.modelo.Curso;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/crear")
public class CrearServlet extends HttpServlet {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("listaCursos", cursoDAO.listar());
            RequestDispatcher dispatcher = req.getRequestDispatcher("crear.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error al cargar formulario de alumno", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            int edad = Integer.parseInt(req.getParameter("edad"));
            int cursoId = Integer.parseInt(req.getParameter("curso_id"));

            Alumno alumno = new Alumno(nombre, email, edad);
            alumno.setCurso(new Curso(cursoId, null, null)); // solo id
            alumnoDAO.insertar(alumno);

            resp.sendRedirect("listar");
        } catch (Exception e) {
            throw new ServletException("Error al crear alumno", e);
        }
    }
}

