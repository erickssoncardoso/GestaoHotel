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
import dao.HotelDAO;
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
import model.Hotel;
import model.Reserva;
import model.Utilizadores;
import model.acomodacao.Acomodacao;
import model.acomodacao.QuartoSimples;
import model.acomodacao.SuitePresidencial;
import services.LoginService;
import util.SessionManager;



public class ConsultaReservaCliente extends javax.swing.JInternalFrame {
  private JLabel tipoLabel;
    private JLabel leitosLabel;
    private JLabel precoLabel;
    private JTextField tipoField;
    private JTextField leitosField;
    private JTextField precoField;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    String username;
    private LoginService loginService;
    /**
     * Creates new form TelaAcomodacao
     */
    public ConsultaReservaCliente(LoginService loginService) {
        this.loginService = loginService;
        initComponents();
        
    }
    
    
    private void atualizarTabelaReservas() {
    HotelDAO hotelDAO = new HotelDAO();
        List<Object[]> hoteis = hotelDAO.buscarTodosHoteis();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar os resultados

        for (Object[] hotel : hoteis) {
            model.addRow(hotel);
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
        txtIdLocalizacaoHotel = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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
                "Nome de Cliente", "Hotel", "Localização", "Id Acomodação", "Tipo Acomodação", "Data Check-In", "Data Check-Out", "Valor (CVE)"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel3.setText("Id Cliente");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Consulta de Reserva por cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtIdLocalizacaoHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)))
                .addGap(0, 592, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdLocalizacaoHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
     String idClienteStr = txtIdLocalizacaoHotel.getText().trim();
    
    if (idClienteStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, insira o ID do cliente.");
        return;
    }
    
    int idCliente;
    try {
        idCliente = Integer.parseInt(idClienteStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID do cliente inválido.");
        return;
    }

    // Chamar o método buscarReservasPorCliente no ReservaDAO
    ReservaDAO reservaDAO = new ReservaDAO();
    List<Object[]> reservas = reservaDAO.buscarReservasPorCliente(idCliente);

    // Atualizar a tabela com os resultados
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Limpa a tabela antes de adicionar os resultados

    if (reservas.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada para o cliente especificado.");
    } else {
        for (Object[] reserva : reservas) {
            model.addRow(reserva);
        }
    }
    }//GEN-LAST:event_btnPesquisarActionPerformed



    
    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    
    }//GEN-LAST:event_jTable1MouseClicked
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtIdLocalizacaoHotel;
    // End of variables declaration//GEN-END:variables
}
