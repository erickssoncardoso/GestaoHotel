package dao;

import conexao.ConexaoBD;
import model.acomodacao.Acomodacao;
import model.acomodacao.QuartoSimples;
import model.acomodacao.SuitePresidencial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcomodacaoDAO {
    // Método para obter todas as acomodações do banco de dados
    public List<Acomodacao> obterTodasAcomodacoes() {
        List<Acomodacao> acomodacoes = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConexaoBD.conectar();

            // Consulta SQL para selecionar todas as acomodações
            String sql = "SELECT * FROM acomodacoes";
            stmt = conexao.prepareStatement(sql);

            // Executar a consulta e obter resultados
            rs = stmt.executeQuery();

            // Processar os resultados e criar objetos Acomodacao
            while (rs.next()) {
                int id = rs.getInt("idacomodacoes");
                String tipo = rs.getString("tipo");
                int quantidadeLeitos = rs.getInt("quantidade_leitos");
                double precoBase = rs.getDouble("preco_base");
                int idhotel = rs.getInt("hotel_id");
                System.out.println("Id Hotel: " + idhotel);

                // Criar objeto Acomodacao com os valores do banco de dados
                Acomodacao acomodacao;
                if (tipo.equals("Simples")) {
                    acomodacao = new QuartoSimples(id, tipo, quantidadeLeitos, precoBase, idhotel);
                } else if (tipo.equals("Presidencial")) {
                    acomodacao = new SuitePresidencial(id, tipo, quantidadeLeitos, precoBase, idhotel);
                } else {
                    // Lidar com tipos de acomodação desconhecidos
                    throw new IllegalArgumentException("Tipo de acomodação desconhecido: " + tipo);
                }

                // Adicionar acomodacao à lista
                acomodacoes.add(acomodacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter acomodações: " + e.getMessage());
        } finally {
            // Fechar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return acomodacoes;
    }
public List<Object[]> buscarAcomodacoesPorHotelEData(int hotelId, java.sql.Date checkinDate, java.sql.Date checkoutDate) {
        List<Object[]> acomodações = new ArrayList<>();
        
        String sql = "SELECT " +
                     "    a.idacomodacoes, " +
                     "    a.tipo, " +
                     "    a.preco_base, " +
                     "    CASE " +
                     "        WHEN r.data_checkin IS NULL THEN 'Disponível' " +
                     "        ELSE 'Ocupado' " +
                     "    END AS estado " +
                     "FROM " +
                     "    acomodacoes a " +
                     "LEFT JOIN " +
                     "    reservas r ON a.idacomodacoes = r.id_acomodacao " +
                     "    AND r.id_hotel = ? " +
                     "    AND ( " +
                     "        (r.data_checkin BETWEEN ? AND ?) OR " +
                     "        (r.data_checkout BETWEEN ? AND ?) OR " +
                     "        (? BETWEEN r.data_checkin AND r.data_checkout) OR " +
                     "        (? BETWEEN r.data_checkin AND r.data_checkout) " +
                     "    ) " +
                     "WHERE " +
                     "    a.hotel_id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hotelId);
            stmt.setDate(2, checkinDate);
            stmt.setDate(3, checkoutDate);
            stmt.setDate(4, checkinDate);
            stmt.setDate(5, checkoutDate);
            stmt.setDate(6, checkinDate);
            stmt.setDate(7, checkoutDate);
            stmt.setInt(8, hotelId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] acomodacao = new Object[4];
                acomodacao[0] = rs.getInt("idacomodacoes");
                acomodacao[1] = rs.getString("tipo");
                acomodacao[2] = rs.getDouble("preco_base");
                acomodacao[3] = rs.getString("estado");
                acomodações.add(acomodacao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return acomodações;
    }
    // Métodos para inserir, atualizar, excluir acomodações...
    // Método para inserir uma nova acomodação no banco de dados
    public void inserirAcomodacao(Acomodacao acomodacao) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConexaoBD.conectar();

            // Consulta SQL para inserir uma acomodação
            String sql = "INSERT INTO acomodacoes (tipo, quantidade_leitos, preco_base, hotel_id) VALUES (?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, acomodacao.getTipo());
            stmt.setInt(2, acomodacao.getQuantidadeLeitos());
            stmt.setDouble(3, acomodacao.getPrecoBase());
            stmt.setInt(4, acomodacao.getIdHotel());
            // Executar a inserção
            stmt.executeUpdate();
            System.out.println("Acomodação inserida com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir acomodação: " + e.getMessage());
        } finally {
            // Fechar recursos
            fecharRecursos(stmt, conexao);
        }
    }

    // Método para atualizar uma acomodação no banco de dados
    public void atualizarAcomodacao(Acomodacao acomodacao) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConexaoBD.conectar();

            // Consulta SQL para atualizar uma acomodação
            String sql = "UPDATE acomodacoes SET tipo = ?, quantidade_leitos = ?, preco_base = ?, hotel_id = ? WHERE idacomodacoes = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, acomodacao.getTipo());
            stmt.setInt(2, acomodacao.getQuantidadeLeitos());
            stmt.setDouble(3, acomodacao.getPrecoBase());
            stmt.setInt(4, acomodacao.getIdHotel());
            // Supondo que acomodacao.getId() retorne o ID da acomodação no banco de dados
            stmt.setInt(5, acomodacao.getId());

            // Executar a atualização
            stmt.executeUpdate();
            System.out.println("Acomodação atualizada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar acomodação: " + e.getMessage());
        } finally {
            // Fechar recursos
            fecharRecursos(stmt, conexao);
        }
    }

    // Método para excluir uma acomodação do banco de dados
    public void excluirAcomodacao(int idAcomodacao) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConexaoBD.conectar();

            // Consulta SQL para excluir uma acomodação
            String sql = "DELETE FROM acomodacoes WHERE idacomodacoes = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAcomodacao);

            // Executar a exclusão
            stmt.executeUpdate();
            System.out.println("Acomodação excluída com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir acomodação: " + e.getMessage());
        } finally {
            // Fechar recursos
            fecharRecursos(stmt, conexao);
        }
    }

    // Método auxiliar para fechar recursos (PreparedStatement e Connection)
    private void fecharRecursos(PreparedStatement stmt, Connection conexao) {
        try {
            if (stmt != null) stmt.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}
