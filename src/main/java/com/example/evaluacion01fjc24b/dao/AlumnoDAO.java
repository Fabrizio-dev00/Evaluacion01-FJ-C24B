package com.example.evaluacion01fjc24b.dao;

import java.sql.*;
import java.util.*;
import com.example.evaluacion01fjc24b.modelo.Alumno;

public class AlumnoDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/evaluacion01?useSSL=false&serverTimezone=UTC";
    private String jdbcUser = "root";
    private String jdbcPass = ""; // En XAMPP root no tiene contraseña

    private static final String INSERT_SQL = "INSERT INTO alumnos (nombre, email, edad) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM alumnos";
    private static final String SELECT_BY_ID = "SELECT * FROM alumnos WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE alumnos SET nombre=?, email=?, edad=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM alumnos WHERE id=?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    public void insertar(Alumno alumno) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getEmail());
            ps.setInt(3, alumno.getEdad());
            ps.executeUpdate();
        }
    }

    public List<Alumno> listar() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("edad")));
            }
        }
        return lista;
    }

    public Alumno buscarPorId(int id) throws SQLException {
        Alumno a = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("edad"));
            }
        }
        return a;
    }

    public void actualizar(Alumno alumno) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getEmail());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
