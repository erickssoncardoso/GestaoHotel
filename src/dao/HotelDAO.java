package dao;

import conexao.ConexaoBD;
import model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.acomodacao.Acomodacao;
import model.acomodacao.QuartoSimples;
import model.acomodacao.SuitePresidencial;

public class HotelDAO {

    public void salvar(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO hoteis (nome, localizacao) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, hotel.getNome());
            stmt.setString(2, hotel.getLocalizacao());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    hotel.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
    public List<Hotel> buscarTodosHoteisAcomodacao() {
        List<Hotel> hoteis = new ArrayList<>();
        String sql = "SELECT id, nome FROM hoteis";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setNome(rs.getString("nome"));
                hoteis.add(hotel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hoteis;
    }

    public List<Hotel> listar() throws SQLException {
        List<Hotel> hoteis = new ArrayList<>();
        String sql = "SELECT DISTINCT id, nome, localizacao FROM hoteis";
        try (Connection connection = ConexaoBD.conectar();
                PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setNome(rs.getString("nome"));
                hotel.setLocalizacao(rs.getString("localizacao"));
                hoteis.add(hotel);
            }
        }
        return hoteis;
    }
    
    public List<String> listarNomesHoteis() {
        List<String> nomesHoteis = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBD.conectar();
            String query = "SELECT DISTINCT nome FROM hoteis";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nomeHotel = rs.getString("nome");
                nomesHoteis.add(nomeHotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoBD.fecharConexao(conn);
        }

        return nomesHoteis;
    }
    
    public List<Object[]> buscarHoteisPorLocalizacao(String localizacao) {
        List<Object[]> hoteis = new ArrayList<>();

        String sql = "SELECT h.nome AS hotel_nome, h.localizacao, a.tipo, a.quantidade_leitos, a.preco_base " +
                     "FROM hoteis h " +
                     "JOIN acomodacoes a ON h.id = a.hotel_id " +
                     "WHERE h.localizacao = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, localizacao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] hotel = {
                        rs.getString("hotel_nome"),
                        rs.getString("localizacao"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade_leitos"),
                        rs.getDouble("preco_base")
                    };
                    hoteis.add(hotel);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoteis;
    }
    
    
        public List<Object[]> buscarHoteisDisponiveisPorData(String localizacao, Date dataCheckin, Date dataCheckout) {
        List<Object[]> hoteis = new ArrayList<>();
        String sql = "SELECT h.id, h.nome, h.localizacao, a.tipo, a.quantidade_leitos, a.preco_base " +
                     "FROM hoteis h " +
                     "INNER JOIN acomodacoes a ON h.id = a.hotel_id " +
                     "WHERE h.localizacao = ? " +
                     "AND h.id NOT IN ( " +
                     "SELECT r.id_hotel " +
                     "FROM reservas r " +
                     "WHERE (r.data_checkin <= ? AND r.data_checkout >= ?) " +
                     "OR (r.data_checkin <= ? AND r.data_checkout >= ?) " +
                     "OR (r.data_checkin >= ? AND r.data_checkout <= ?) " +
                     ")";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, localizacao);
            stmt.setDate(2, dataCheckout);
            stmt.setDate(3, dataCheckin);
            stmt.setDate(4, dataCheckout);
            stmt.setDate(5, dataCheckin);
            stmt.setDate(6, dataCheckin);
            stmt.setDate(7, dataCheckout);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] hotel = new Object[6];
                    hotel[0] = rs.getInt(1);    // id do hotel
                    hotel[1] = rs.getString(2); // nome do hotel
                    hotel[2] = rs.getString(3); // localização do hotel
                    hotel[3] = rs.getString(4); // tipo de acomodação
                    hotel[4] = rs.getInt(5);    // número de camas
                    hotel[5] = rs.getDouble(6); // preço
                    hoteis.add(hotel);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return hoteis;
    }
    
    


public List<Object[]> buscarTodosHoteis() {
        List<Object[]> hoteis = new ArrayList<>();

        String sql = "SELECT h.nome AS hotel_nome, h.localizacao, a.tipo, a.quantidade_leitos, a.preco_base " +
                       "FROM hoteis h " +
                       "JOIN acomodacoes a ON h.id = a.hotel_id";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] hotel = {
                        rs.getString("hotel_nome"),
                        rs.getString("localizacao"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade_leitos"),
                        rs.getDouble("preco_base")
                    };
                    hoteis.add(hotel);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoteis;
    }
    public void atualizar(Hotel hotel) throws SQLException {
        String sql = "UPDATE hoteis SET nome = ?, localizacao = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hotel.getNome());
            stmt.setString(2, hotel.getLocalizacao());
            stmt.setInt(3, hotel.getId());
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM hoteis WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
  
    public List<Hotel> listarHoteisDisponiveis(String localizacao, Date dataCheckin, Date dataCheckout) {
        List<Hotel> hoteis = new ArrayList<>();
        String sql = "SELECT * FROM hoteis WHERE localizacao = ? AND id NOT IN (" +
                     "SELECT id_hotel FROM reservas " +
                     "WHERE (? BETWEEN data_checkin AND data_checkout) OR (? BETWEEN data_checkin AND data_checkout))";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, localizacao);
            stmt.setDate(2, dataCheckin);
            stmt.setDate(3, dataCheckout);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setLocalizacao(rs.getString("localizacao"));
                hoteis.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoteis;
    }
    
    public List<String> listarLocalizacoes() throws SQLException {
        List<String> localizacoes = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConexaoBD.conectar();
            
            // Consulta SQL para selecionar localizações distintas da tabela de hotéis
            String sql = "SELECT DISTINCT localizacao FROM hoteis";
            
            // Preparar a instrução SQL
            stmt = conexao.prepareStatement(sql);
            
            // Executar a consulta
            rs = stmt.executeQuery();
            
            // Iterar sobre o resultado e adicionar cada localização à lista
            while (rs.next()) {
                String localizacao = rs.getString("localizacao");
                localizacoes.add(localizacao);
            }
        } finally {
            // Fechar os recursos
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }
        
        return localizacoes;
    }
    public List<Hotel> listarHoteisDisponiveisPorUsuario(int idUsuario) throws SQLException {
    List<Hotel> hoteisDisponiveis = new ArrayList<>();

    // Consulta SQL para obter os hotéis disponíveis para o usuário
    String query = "SELECT h.* FROM Hoteis h "
                 + "INNER JOIN Reservas r ON h.id = r.id_hotel "
                 + "WHERE r.id_cliente = ? "
                 + "AND r.data_checkin <= CURRENT_DATE "
                 + "AND r.data_checkout >= CURRENT_DATE";

    try (Connection conexao = ConexaoBD.conectar();
         PreparedStatement stmt = conexao.prepareStatement(query)) {

        // Definir o parâmetro ID do usuário na consulta SQL
        stmt.setInt(1, idUsuario);

        // Executar a consulta e obter o resultado
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Para cada linha do resultado, criar um objeto Hotel e adicioná-lo à lista
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String localizacao = rs.getString("localizacao");

                Hotel hotel = new Hotel(id, nome, localizacao);
                hoteisDisponiveis.add(hotel);
            }
        }
    }

    return hoteisDisponiveis;
}
    // Método para buscar o ID do hotel pelo nome
    public int buscarIdPeloNome(String nomeHotel) {
        int idHotel = 0; // Valor padrão para o caso de não encontrar nenhum hotel

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBD.conectar(); // Obtém a conexão com o banco de dados
            String query = "SELECT id FROM hoteis WHERE nome = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nomeHotel); // Define o parâmetro na query
            rs = stmt.executeQuery();

            if (rs.next()) {
                idHotel = rs.getInt("id"); // Obtém o ID do hotel do resultado da consulta
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata qualquer exceção SQL imprimindo o stack trace
        } finally {
            // Fecha os recursos JDBC em um bloco finally para garantir que sejam fechados corretamente
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Trata qualquer exceção ao fechar recursos imprimindo o stack trace
            }
        }

        return idHotel; // Retorna o ID do hotel encontrado (ou 0 se não encontrou nenhum)
    }

}
