package com.example.evaluacion01fjc24b.dao;

import java.sql.*;
import java.util.*;
import com.example.evaluacion01fjc24b.modelo.Alumno;
import com.example.evaluacion01fjc24b.modelo.Curso;

public class AlumnoDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/evaluacion01?useSSL=false&serverTimezone=UTC";
    private String jdbcUser = "root";
    private String jdbcPass = ""; // En XAMPP root no tiene contraseña

    // SQL con relación a cursos
    private static final String INSERT_SQL =
            "INSERT INTO alumnos (nombre, email, edad, curso_id) VALUES (?, ?, ?, ?)";

    private static final String SELECT_ALL =
            "SELECT a.id, a.nombre, a.email, a.edad, " +
                    "c.id AS curso_id, c.nombre AS curso_nombre, c.descripcion " +
                    "FROM alumnos a LEFT JOIN cursos c ON a.curso_id = c.id";

    private static final String SELECT_BY_ID =
            "SELECT a.id, a.nombre, a.email, a.edad, " +
                    "c.id AS curso_id, c.nombre AS curso_nombre, c.descripcion " +
                    "FROM alumnos a LEFT JOIN cursos c ON a.curso_id = c.id " +
                    "WHERE a.id=?";

    private static final String UPDATE_SQL =
            "UPDATE alumnos SET nombre=?, email=?, edad=?, curso_id=? WHERE id=?";

    private static final String DELETE_SQL =
            "DELETE FROM alumnos WHERE id=?";



    // Conexión
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    // Insertar
    public void insertar(Alumno alumno) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getEmail());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getCurso().getId()); // clave foránea
            ps.executeUpdate();
        }
    }

    // Listar todos
    public List<Alumno> listar() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("curso_id"),
                        rs.getString("curso_nombre"),
                        rs.getString("descripcion")
                );

                Alumno alumno = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("edad"),
                        curso
                );
                lista.add(alumno);
            }
        }
        return lista;
    }

    // Buscar por ID
    public Alumno buscarPorId(int id) throws SQLException {
        Alumno a = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("curso_id"),
                        rs.getString("curso_nombre"),
                        rs.getString("descripcion")
                );
                a = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("edad"),
                        curso
                );
            }
        }
        return a;
    }

    // Actualizar
    public void actualizar(Alumno alumno) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getEmail());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getCurso().getId());
            ps.setInt(5, alumno.getId());
            ps.executeUpdate();
        }
    }

    // Eliminar
    public void eliminar(int id) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
