package dao;

import conexao.ConexaoBD;
import model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    
public List<Object[]> buscarReservasPorUsuario(int idUsuario) {
    List<Object[]> reservas = new ArrayList<>();

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(
             "SELECT reservas.id, utilizadores.nome, hoteis.nome, hoteis.localizacao, acomodacoes.tipo, reservas.data_checkin, reservas.data_checkout, utilizadores.id " +
             "FROM acomodacoes " +
             "INNER JOIN (utilizadores " +
             "INNER JOIN (reservas " +
             "INNER JOIN hoteis ON reservas.id_hotel = hoteis.id) " +
             "ON utilizadores.id = reservas.id_cliente) " +
             "ON acomodacoes.idacomodacoes = reservas.id_acomodacao " +
             "WHERE utilizadores.id = ?")) {

        stmt.setInt(1, idUsuario);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Object[] reserva = new Object[8];
                reserva[0] = rs.getInt(1); // id da reserva
                reserva[1] = rs.getString(2); // nome do usuário
                reserva[2] = rs.getString(3); // nome do hotel
                reserva[3] = rs.getString(4); // localização do hotel
                reserva[4] = rs.getString(5); // tipo de acomodação
                reserva[5] = rs.getDate(6); // data de check-in
                reserva[6] = rs.getDate(7); // data de check-out
                reserva[7] = rs.getInt(8); // id do usuário
                reservas.add(reserva);
            }
        }
    } catch (SQLException ex) {
        // Tratar exceções
        ex.printStackTrace();
    }

    return reservas;
}
    public void inserirReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas (id_cliente, id_hotel, id_acomodacao, data_checkin, data_checkout) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, reserva.getIdCliente());
            stmt.setInt(2, reserva.getIdHotel());
            stmt.setInt(3, reserva.getIdAcomodacao());
            stmt.setDate(4, new java.sql.Date(reserva.getDataCheckin().getTime()));
            stmt.setDate(5, new java.sql.Date(reserva.getDataCheckout().getTime()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir reserva, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reserva.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir reserva, ID não obtido.");
                }
            }

            System.out.println("Reserva inserida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir reserva: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Reserva> obterTodasReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setIdCliente(rs.getInt("id_cliente"));
                reserva.setIdHotel(rs.getInt("id_hotel"));
                reserva.setIdAcomodacao(rs.getInt("id_acomodacao"));
                reserva.setDataCheckin(rs.getDate("data_checkin"));
                reserva.setDataCheckout(rs.getDate("data_checkout"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter reservas: " + e.getMessage());
            e.printStackTrace();
        }

        return reservas;
    }
    public boolean verificarDisponibilidadeAcomodacao(int idAcomodacao, Date dataCheckin, Date dataCheckout) {
    String sql = "SELECT COUNT(*) FROM reservas WHERE id_acomodacao = ? AND " +
                 "((data_checkin <= ? AND data_checkout >= ?) OR " +
                 "(data_checkin <= ? AND data_checkout >= ?) OR " +
                 "(data_checkin >= ? AND data_checkout <= ?))";
    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idAcomodacao);
        stmt.setDate(2, dataCheckout);
        stmt.setDate(3, dataCheckin);
        stmt.setDate(4, dataCheckin);
        stmt.setDate(5, dataCheckout);
        stmt.setDate(6, dataCheckin);
        stmt.setDate(7, dataCheckout);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    

    
    public boolean verificarExistenciaCliente(int idCliente) {
    String sql = "SELECT COUNT(*) FROM utilizadores WHERE id = ?";
    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idCliente);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean verificarExistenciaHotel(int idHotel) {
    String sql = "SELECT COUNT(*) FROM hoteis WHERE id = ?";
    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idHotel);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean verificarExistenciaAcomodacao(int idAcomodacao) {
    String sql = "SELECT COUNT(*) FROM acomodacoes WHERE idacomodacoes = ?";
    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idAcomodacao);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
 // Método para excluir uma reserva pelo seu ID
    public boolean excluirReserva(int idReserva) {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir reserva: " + e.getMessage());
            return false;
        }
    }
    
    public List<Object[]> buscarReservasPorCliente(int idCliente) {
        List<Object[]> reservas = new ArrayList<>();
        String sql = "SELECT utilizadores.nome AS nomeCliente, hoteis.nome AS nomeHotel, hoteis.localizacao, " +
                     "acomodacoes.idacomodacoes, acomodacoes.tipo, reservas.data_checkin, reservas.data_checkout, acomodacoes.preco_base " +
                     "FROM utilizadores INNER JOIN (acomodacoes INNER JOIN (hoteis INNER JOIN reservas ON hoteis.id = reservas.id_hotel) " +
                     "ON acomodacoes.idacomodacoes = reservas.id_acomodacao) ON utilizadores.id = reservas.id_cliente " +
                     "WHERE utilizadores.id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] reserva = new Object[8];  // atualizado para 8 colunas
                reserva[0] = rs.getString("nomeCliente");
                reserva[1] = rs.getString("nomeHotel");
                reserva[2] = rs.getString("localizacao");
                reserva[3] = rs.getInt("idacomodacoes");
                reserva[4] = rs.getString("tipo");
                reserva[5] = rs.getDate("data_checkin");
                reserva[6] = rs.getDate("data_checkout");
                reserva[7] = rs.getDouble("preco_base");
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
    public List<Object[]> buscarReservasPorHotel(int idHotel) {
        List<Object[]> reservas = new ArrayList<>();
        String sql = "SELECT reservas.id, utilizadores.id AS idCliente, utilizadores.nome AS nomeCliente, " +
                     "hoteis.nome AS nomeHotel, hoteis.localizacao, acomodacoes.idacomodacoes, " +
                     "acomodacoes.tipo, reservas.data_checkin, reservas.data_checkout " +
                     "FROM utilizadores INNER JOIN (acomodacoes INNER JOIN (hoteis INNER JOIN reservas " +
                     "ON hoteis.id = reservas.id_hotel) ON acomodacoes.idacomodacoes = reservas.id_acomodacao) " +
                     "ON utilizadores.id = reservas.id_cliente " +
                     "WHERE hoteis.id = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idHotel);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] reserva = new Object[9];  // atualizado para 9 colunas
                reserva[0] = rs.getInt("id");
                reserva[1] = rs.getInt("idCliente");
                reserva[2] = rs.getString("nomeCliente");
                reserva[3] = rs.getString("nomeHotel");
                reserva[4] = rs.getString("localizacao");
                reserva[5] = rs.getInt("idacomodacoes");
                reserva[6] = rs.getString("tipo");
                reserva[7] = rs.getDate("data_checkin");
                reserva[8] = rs.getDate("data_checkout");
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
    
    public boolean atualizarReserva(int idReserva, int idCliente, int idHotel, int idAcomodacao, Date dataCheckin, Date dataCheckout) {
        String sql = "UPDATE reservas SET id_cliente=?, id_hotel=?, id_acomodacao=?, data_checkin=?, data_checkout=? WHERE id=?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setInt(2, idHotel);
            stmt.setInt(3, idAcomodacao);
            stmt.setDate(4, new java.sql.Date(dataCheckin.getTime()));
            stmt.setDate(5, new java.sql.Date(dataCheckout.getTime()));
            stmt.setInt(6, idReserva);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar reserva: " + e.getMessage());
            return false;
        }
    }



}
