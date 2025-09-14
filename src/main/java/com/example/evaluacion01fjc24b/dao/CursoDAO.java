package com.example.evaluacion01fjc24b.dao;

import com.example.evaluacion01fjc24b.modelo.Curso;
import java.sql.*;
import java.util.*;

public class CursoDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/evaluacion01?useSSL=false&serverTimezone=UTC";
    private String jdbcUser = "root";
    private String jdbcPass = "";

    private static final String SELECT_ALL = "SELECT * FROM cursos";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    public List<Curso> listar() throws SQLException {
        List<Curso> lista = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }
        }
        return lista;
    }
}
