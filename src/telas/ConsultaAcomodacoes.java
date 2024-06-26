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



public class ConsultaAcomodacoes extends javax.swing.JInternalFrame {
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
     private List<Hotel> listaHoteis; // Declaração da variável listaHoteis
    /**
     * Creates new form TelaAcomodacao
     */
    public ConsultaAcomodacoes(LoginService loginService) {
        this.loginService = loginService;
        initComponents();
        //atualizarTabelaReservas();
        carregarHoteis();
        
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
    private void carregarHoteis() {
    HotelDAO hotelDAO = new HotelDAO();
    listaHoteis = hotelDAO.buscarTodosHoteisAcomodacao();

    ComboHotel.removeAllItems();
    for (Hotel hotel : listaHoteis) {
        ComboHotel.addItem(hotel.getNome());
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
        jLabel2 = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DateCheckout = new com.toedter.calendar.JDateChooser();
        DateCheckin = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        ComboHotel = new javax.swing.JComboBox<>();

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
                "Id Acomodação", "Tipo", "Preço", "Estado"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Data Check-Out");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Disponibilidade de Acomodações");

        jLabel6.setText("Data Check-In");

        jLabel4.setText("Hotel");

        ComboHotel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(DateCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DateCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisar)))))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(DateCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ComboHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DateCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(405, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
      try {
        String hotelNome = (String) ComboHotel.getSelectedItem();
        if (hotelNome == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um hotel.");
            return;
        }

        // Encontra o hotel selecionado na lista de hotéis
        Hotel selectedHotel = null;
        for (Hotel hotel : listaHoteis) {
            if (hotel.getNome().equals(hotelNome)) {
                selectedHotel = hotel;
                break;
            }
        }

        if (selectedHotel == null) {
            JOptionPane.showMessageDialog(this, "Hotel não encontrado.");
            return;
        }

        int hotelId = selectedHotel.getId();
        Date checkinDate = DateCheckin.getDate();
        Date checkoutDate = DateCheckout.getDate();

        if (checkinDate == null || checkoutDate == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione datas de check-in e check-out.");
            return;
        }

        // Formatação das datas para o formato SQL
        java.sql.Date sqlCheckinDate = new java.sql.Date(checkinDate.getTime());
        java.sql.Date sqlCheckoutDate = new java.sql.Date(checkoutDate.getTime());

        // Chame o método do DAO para buscar as acomodações
        AcomodacaoDAO acomodacaoDAO = new AcomodacaoDAO();
        List<Object[]> acomodacoes = acomodacaoDAO.buscarAcomodacoesPorHotelEData(hotelId, sqlCheckinDate, sqlCheckoutDate);

        // Atualize a tabela
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar os novos resultados

        for (Object[] acomodacao : acomodacoes) {
            model.addRow(acomodacao);
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao buscar acomodações: " + e.getMessage());
    }

    }//GEN-LAST:event_btnPesquisarActionPerformed



    
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

       
        DateCheckin.setDate(dataCheckin);
        DateCheckout.setDate(dataCheckout);
    }
    }//GEN-LAST:event_jTable1MouseClicked
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboHotel;
    private com.toedter.calendar.JDateChooser DateCheckin;
    private com.toedter.calendar.JDateChooser DateCheckout;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
