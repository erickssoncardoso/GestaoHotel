/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package telas;

/**
 *
 * @author ecardoso
 */
import dao.AcomodacaoDAO;
import dao.ReservaDAO;
import dao.UtilizadoresDAO;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Funcionario;
import model.Reserva;
import model.Utilizadores;
import model.acomodacao.Acomodacao;
import model.acomodacao.QuartoSimples;
import model.acomodacao.SuitePresidencial;



public class TelaReserva extends javax.swing.JInternalFrame {
  private JLabel tipoLabel;
    private JLabel leitosLabel;
    private JLabel precoLabel;
    private JTextField tipoField;
    private JTextField leitosField;
    private JTextField precoField;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    /**
     * Creates new form TelaAcomodacao
     */
    public TelaReserva() {
        initComponents();
        atualizarTabelaReservas();
        
    }
    
    private void atualizarTabelaReservas() {
      ReservaDAO reservaDAO = new ReservaDAO();
    List<Reserva> reservas = reservaDAO.obterTodasReservas();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);

    for (Reserva reserva : reservas) {
        model.addRow(new Object[]{
            reserva.getId(),
            reserva.getIdCliente(),
            reserva.getIdHotel(),
            reserva.getIdAcomodacao(),
            reserva.getDataCheckin(),
            reserva.getDataCheckout()
        });
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtIdHotel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdAcomodacao = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DateCheckout = new com.toedter.calendar.JDateChooser();
        DateCheckin = new com.toedter.calendar.JDateChooser();
        txtIdReserva = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Reserva", "Id_Cliente", "id_Hotel", "Id_Acomodação", "Data Check-in", "Data Check-out"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("ID Cliente");

        jLabel2.setText("Data Check-Out");

        jLabel4.setText("ID Acomodação");

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jLabel3.setText("ID Hotel");

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Cancelar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Gestão de Reservas");

        jLabel6.setText("Data Check-In");

        txtIdReserva.setEditable(false);
        txtIdReserva.setEnabled(false);

        jLabel7.setText("ID Reserva");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdAcomodacao)
                                    .addComponent(txtIdHotel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(DateCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(DateCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnAdicionar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAtualizar)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdAcomodacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(DateCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdicionar)
                            .addComponent(btnEliminar)
                            .addComponent(btnAtualizar))
                        .addGap(48, 48, 48))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
       int idCliente = Integer.parseInt(txtIdCliente.getText());
    int idHotel = Integer.parseInt(txtIdHotel.getText());
    int idAcomodacao = Integer.parseInt(txtIdAcomodacao.getText());
    Date dataCheckin = new Date(DateCheckin.getDate().getTime());
    Date dataCheckout = new Date(DateCheckout.getDate().getTime());

    ReservaDAO reservaDAO = new ReservaDAO();

    if (!reservaDAO.verificarExistenciaCliente(idCliente)) {
        JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
        return;
    }

    if (!reservaDAO.verificarExistenciaHotel(idHotel)) {
        JOptionPane.showMessageDialog(this, "Hotel não encontrado!");
        return;
    }

    if (!reservaDAO.verificarExistenciaAcomodacao(idAcomodacao)) {
        JOptionPane.showMessageDialog(this, "Acomodação não encontrada!");
        return;
    }

    // Convertendo java.util.Date para java.sql.Date
java.sql.Date dataCheckinSQL = new java.sql.Date(dataCheckin.getTime());
java.sql.Date dataCheckoutSQL = new java.sql.Date(dataCheckout.getTime());

// Verificando a disponibilidade da acomodação
if (!reservaDAO.verificarDisponibilidadeAcomodacao(idAcomodacao, dataCheckinSQL, dataCheckoutSQL)) {
    JOptionPane.showMessageDialog(this, "Acomodação não disponível para as datas selecionadas!");
    return;
}

    Reserva reserva = new Reserva(idCliente, idHotel, idAcomodacao, dataCheckin, dataCheckout);

    try {
        reservaDAO.inserirReserva(reserva);
        JOptionPane.showMessageDialog(this, "Reserva adicionada com sucesso!");
        atualizarTabelaReservas();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao inserir reserva: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAdicionarActionPerformed



    
    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
            // Obtém o índice da linha selecionada
    int rowIndex = jTable1.getSelectedRow();

    // Verifica se uma linha foi realmente selecionada
    if (rowIndex != -1) {
        // Obtém os dados da reserva selecionada
        int idReserva = (int) jTable1.getValueAt(rowIndex, 0);
        int idCliente = (int) jTable1.getValueAt(rowIndex, 1);
        int idHotel = (int) jTable1.getValueAt(rowIndex, 2);
        int idAcomodacao = (int) jTable1.getValueAt(rowIndex, 3);
        Date dataCheckin = (Date) jTable1.getValueAt(rowIndex, 4);
        Date dataCheckout = (Date) jTable1.getValueAt(rowIndex, 5);

        // Exibe os dados nos campos de texto ou em outros componentes
        txtIdReserva.setText(String.valueOf(idReserva));
        txtIdCliente.setText(String.valueOf(idCliente));
        txtIdHotel.setText(String.valueOf(idHotel));
        txtIdAcomodacao.setText(String.valueOf(idAcomodacao));
        DateCheckin.setDate(dataCheckin);
        DateCheckout.setDate(dataCheckout);
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
            int idReserva = Integer.parseInt(txtIdReserva.getText());
    int idCliente = Integer.parseInt(txtIdCliente.getText());
    int idHotel = Integer.parseInt(txtIdHotel.getText());
    int idAcomodacao = Integer.parseInt(txtIdAcomodacao.getText());
    Date dataCheckin = DateCheckin.getDate();
    Date dataCheckout = DateCheckout.getDate();

    // Verificar se todos os campos foram preenchidos
    if (idCliente == 0 || idHotel == 0 || idAcomodacao == 0 || dataCheckin == null || dataCheckout == null) {
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        return;
    }
       
// Criar uma instância de ReservaDAO
ReservaDAO reservaDAO = new ReservaDAO();

// Converter java.util.Date para java.sql.Date
java.sql.Date dataCheckinSql = new java.sql.Date(dataCheckin.getTime());
java.sql.Date dataCheckoutSql = new java.sql.Date(dataCheckout.getTime());

// Verificar se a acomodação está disponível para as datas selecionadas
if (!reservaDAO.verificarDisponibilidadeAcomodacao(idAcomodacao, dataCheckinSql, dataCheckoutSql)) {
    JOptionPane.showMessageDialog(this, "Acomodação não disponível para as datas selecionadas!");
    return;
}

    // Criar um objeto Reserva com os novos dados
    Reserva reserva = new Reserva(idCliente, idHotel, idAcomodacao, dataCheckin, dataCheckout);

// Converter java.util.Date para java.sql.Date


// Atualizar a reserva no banco de dados
if (reservaDAO.atualizarReserva(idReserva, idCliente, idHotel, idAcomodacao, dataCheckinSql, dataCheckoutSql)) {
    JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!");
    atualizarTabelaReservas(); // Atualizar a tabela após a atualização
} else {
    JOptionPane.showMessageDialog(this, "Erro ao atualizar reserva!");
}
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
            int linhaSelecionada = jTable1.getSelectedRow();
    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecione uma reserva para excluir.");
        return;
    }

    int idReserva = (int) jTable1.getValueAt(linhaSelecionada, 0);
    if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta reserva?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        ReservaDAO reservaDAO = new ReservaDAO();
        if (reservaDAO.excluirReserva(idReserva)) {
            JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso!");
            atualizarTabelaReservas();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir reserva.");
        }
    }
    }//GEN-LAST:event_btnEliminarActionPerformed
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateCheckin;
    private com.toedter.calendar.JDateChooser DateCheckout;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtIdAcomodacao;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdHotel;
    private javax.swing.JTextField txtIdReserva;
    // End of variables declaration//GEN-END:variables
}
