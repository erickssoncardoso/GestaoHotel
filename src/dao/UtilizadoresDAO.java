package dao;

import conexao.ConexaoBD;
import model.Cliente;
import model.Funcionario;
import model.Utilizadores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizadoresDAO {

    public List<Utilizadores> obterTodosUtilizadores() {
        List<Utilizadores> utilizadores = new ArrayList<>();
        String sql = "SELECT u.id, u.nome, u.email, u.telefone, u.tipo, us.username, us.senha " +
                     "FROM utilizadores u " +
                     "JOIN users us ON u.id = us.utilizador_id";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int telefone = rs.getInt("telefone");
                String tipo = rs.getString("tipo");
                String username = rs.getString("username");
                String senha = rs.getString("senha");

                Utilizadores utilizador;
                if (tipo.equals("Cliente")) {
                    utilizador = new Cliente(id, nome, email, telefone, tipo, username, senha);
                } else if (tipo.equals("Funcionario")) {
                    utilizador = new Funcionario(id, nome, email, telefone, tipo, username, senha);
                } else {
                    throw new IllegalArgumentException("Tipo de utilizador desconhecido: " + tipo);
                }

                utilizadores.add(utilizador);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter utilizadores: " + e.getMessage());
            e.printStackTrace();
        }

        return utilizadores;
    }
    
    public boolean isCliente(String username) {
        return verificarTipoUsuario(username, "Cliente");
    }
    
    public boolean isFuncionario(String username) {
        return verificarTipoUsuario(username, "Funcionario");
    }

    private boolean verificarTipoUsuario(String username, String tipoEsperado) {
        String query = "SELECT tu.tipo FROM users u JOIN utilizadores tu ON u.utilizador_id = tu.id WHERE u.username = ?";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    return tipo.equals(tipoEsperado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void adicionarUtilizador(Utilizadores utilizador) throws SQLException {
        String sqlUtilizadores = "INSERT INTO utilizadores (nome, email, telefone, tipo) VALUES (?, ?, ?, ?)";
        String sqlUsers = "INSERT INTO users (username, senha, utilizador_id) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmtUtilizadores = conn.prepareStatement(sqlUtilizadores, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtUsers = conn.prepareStatement(sqlUsers)) {

            conn.setAutoCommit(false);

            pstmtUtilizadores.setString(1, utilizador.getNome());
            pstmtUtilizadores.setString(2, utilizador.getEmail());
            pstmtUtilizadores.setInt(3, utilizador.getTelefone());
            pstmtUtilizadores.setString(4, utilizador.getTipo());
            pstmtUtilizadores.executeUpdate();

            try (ResultSet rs = pstmtUtilizadores.getGeneratedKeys()) {
                if (rs.next()) {
                    int utilizadorId = rs.getInt(1);

                    pstmtUsers.setString(1, utilizador.getUsername());
                    pstmtUsers.setString(2, utilizador.getPassword());
                    pstmtUsers.setInt(3, utilizadorId);
                    pstmtUsers.executeUpdate();

                    conn.commit();
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void atualizarUtilizador(Utilizadores utilizador) throws SQLException {
        String sqlUtilizadores = "UPDATE utilizadores SET nome = ?, email = ?, telefone = ?, tipo = ? WHERE id = ?";
        String sqlUsers = "UPDATE users SET username = ?, senha = ? WHERE utilizador_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmtUtilizadores = conn.prepareStatement(sqlUtilizadores);
             PreparedStatement pstmtUsers = conn.prepareStatement(sqlUsers)) {

            conn.setAutoCommit(false);

            pstmtUtilizadores.setString(1, utilizador.getNome());
            pstmtUtilizadores.setString(2, utilizador.getEmail());
            pstmtUtilizadores.setInt(3, utilizador.getTelefone());
            pstmtUtilizadores.setString(4, utilizador.getTipo());
            pstmtUtilizadores.setInt(5, utilizador.getId());
            pstmtUtilizadores.executeUpdate();

            pstmtUsers.setString(1, utilizador.getUsername());
            pstmtUsers.setString(2, utilizador.getPassword());
            pstmtUsers.setInt(3, utilizador.getId());
            pstmtUsers.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void eliminarUtilizador(int id) throws SQLException {
        String sqlUsers = "DELETE FROM users WHERE utilizador_id = ?";
        String sqlUtilizadores = "DELETE FROM utilizadores WHERE id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmtUsers = conn.prepareStatement(sqlUsers);
             PreparedStatement pstmtUtilizadores = conn.prepareStatement(sqlUtilizadores)) {

            conn.setAutoCommit(false);

            pstmtUsers.setInt(1, id);
            pstmtUsers.executeUpdate();

            pstmtUtilizadores.setInt(1, id);
            pstmtUtilizadores.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public int buscarIdPeloUsername(String username) {
    int id = -1; // Valor padrão se não encontrar o usuário

    try (Connection conn =ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement("SELECT utilizador_id FROM users WHERE username = ?")) {

        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt("utilizador_id");
            }
        }
    } catch (SQLException ex) {
        // Tratar exceções
        ex.printStackTrace();
    }

    return id;
}
}
