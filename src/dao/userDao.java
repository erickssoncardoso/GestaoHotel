package dao;

import conexao.ConexaoBD;
import model.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDao {
    // Consulta SQL para verificar as credenciais do usuário
    private static final String SQL_LOGIN = "SELECT * FROM users WHERE username = ? AND senha = ?";

    // Método para verificar as credenciais do usuário no banco de dados
    public static boolean fazerLogin(String username, String password) {
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(SQL_LOGIN)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se encontrar um usuário com as credenciais fornecidas
        } catch (SQLException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
            return false;
        }
    }
}
