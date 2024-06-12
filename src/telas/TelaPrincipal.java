/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import conexao.ConexaoBD;
import dao.UtilizadoresDAO;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import services.LoginService;
import services.TelaPrincipalServico;
import util.SessionManager;

/**
 *
 * @author ecardoso
 */
public class TelaPrincipal extends javax.swing.JFrame {

    private String username;
    private TelaPrincipalServico telaPrincipalServico;
    
    public TelaPrincipal(String username) {
        this.username = username;
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        telaPrincipalServico = new TelaPrincipalServico();
        labelUsername.setText(username);
        telaPrincipalServico.iniciarRelogio(jLabel3);
        telaPrincipalServico.atualizarMenu(username, jMenuBar1, menuCadHospede, menuCadQuartos);
    }
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
     initComponents();
    setExtendedState(MAXIMIZED_BOTH);
    telaPrincipalServico = new TelaPrincipalServico();
    telaPrincipalServico.iniciarRelogio(jLabel3);
    atualizarMenu();
    }
    private void atualizarMenu() {
    if (username == null) {
        jMenu6.setVisible(true);
        jMenu1.setVisible(false);
        jMenu4.setVisible(false);
        jMenu5.setVisible(false);
        jMenu7.setVisible(false);
        jMenu2.setVisible(false);
        menuSair.setVisible(false);
        menuLogin.setVisible(true);
        return;
    }
    
    UtilizadoresDAO userDAO = new UtilizadoresDAO();
    if (userDAO.isCliente(username)) {
        menuSair.setVisible(true);
        menuLogin.setVisible(false);
        menuCadHospede.setEnabled(false);
        menuCadQuartos.setEnabled(false);
        jMenu1.setVisible(false);
        jMenu4.setVisible(false);
        jMenu5.setVisible(false);
        jMenu6.setVisible(false);
        jMenu7.setVisible(false);
    } else if (userDAO.isFuncionario(username)) {
        menuLogin.setVisible(false);
        menuCadHospede.setEnabled(true);
        menuCadQuartos.setEnabled(true);
        menuSair.setEnabled(true);
    }
}
    Connection conexao = null;
    
    
    
    
    
    
 private void updateClock() {
        Thread clockThread = new Thread(() -> {
            try {
                while (true) {
                    LocalTime currentTime = LocalTime.now();
                    String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    jLabel3.setText("Horário de Sistema: " + formattedTime);
                    Thread.sleep(1000); 
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        clockThread.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        telaFundo = new javax.swing.JDesktopPane();
        labeltelafundo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        menuCadHospede = new javax.swing.JMenuItem();
        menuConsHospede = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        menuCadQuartos = new javax.swing.JMenuItem();
        menuConsQuartos = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        menuSair = new javax.swing.JMenu();
        menuLogin = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Hotel Piaget");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Utilizador:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Acesso:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Cargo:");

        labelUsername.setText("Nome");

        jLabel8.setText("texto");

        jLabel9.setText("texto");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo_bola.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(jSeparator5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 149, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(405, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        labeltelafundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Fundo Piaget.jpg"))); // NOI18N

        telaFundo.setLayer(labeltelafundo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout telaFundoLayout = new javax.swing.GroupLayout(telaFundo);
        telaFundo.setLayout(telaFundoLayout);
        telaFundoLayout.setHorizontalGroup(
            telaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labeltelafundo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        telaFundoLayout.setVerticalGroup(
            telaFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, telaFundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labeltelafundo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastro.png"))); // NOI18N
        jMenu1.setText("Cadastros   |");

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_hospede.png"))); // NOI18N
        jMenu9.setText("Hospedes");

        menuCadHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_salvar.png"))); // NOI18N
        menuCadHospede.setText("Cadastrar");
        jMenu9.add(menuCadHospede);

        menuConsHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_buscar.png"))); // NOI18N
        menuConsHospede.setText("Consultar");
        jMenu9.add(menuConsHospede);

        jMenu1.add(jMenu9);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_quarto.png"))); // NOI18N
        jMenu10.setText("Quartos");

        menuCadQuartos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_quarto.png"))); // NOI18N
        menuCadQuartos.setText("Cadastrar");
        menuCadQuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadQuartosActionPerformed(evt);
            }
        });
        jMenu10.add(menuCadQuartos);

        menuConsQuartos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/buscar.png"))); // NOI18N
        menuConsQuartos.setText("Consultar");
        jMenu10.add(menuConsQuartos);

        jMenu1.add(jMenu10);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_funcionario.png"))); // NOI18N
        jMenu8.setText("Utilizadores");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_salvar.png"))); // NOI18N
        jMenuItem2.setText("Cadastrar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/buscar.png"))); // NOI18N
        jMenuItem3.setText("Consultar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenu1.add(jMenu8);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/hotel (2).png"))); // NOI18N
        jMenu11.setText("Hoteis");

        jMenuItem8.setText("Registrar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem8);

        jMenuItem9.setText("Consultar");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseClicked(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem9);

        jMenu1.add(jMenu11);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/reservas.png"))); // NOI18N
        jMenu2.setText("Reservas   |");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_checkin.png"))); // NOI18N
        jMenuItem4.setText("Cadastrar Reserva");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_Con_checkin.png"))); // NOI18N
        jMenuItem5.setText("Consultar Reserva");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_checkout.png"))); // NOI18N
        jMenuItem6.setText("Finalizar Reserva");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/financeiro.png"))); // NOI18N
        jMenu4.setText("Financeiro   |");
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/relatorios.png"))); // NOI18N
        jMenu5.setText("Relatórios   |");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_quarto.png"))); // NOI18N
        jMenu3.setText("Acomodações");

        jMenuItem1.setText("Disponibilidade de Acomodações");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenu5.add(jMenu3);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_checkin.png"))); // NOI18N
        jMenu12.setText("Reservas");

        jMenuItem7.setText("Reserva por Cliente");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem7);

        jMenuItem12.setText("Reserva por Hotel");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem12);

        jMenu5.add(jMenu12);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon_quarto.png"))); // NOI18N
        jMenu6.setText("Estadias");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ferramentas.png"))); // NOI18N
        jMenu7.setText("Ferramentas");
        jMenuBar1.add(jMenu7);

        menuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sair.png"))); // NOI18N
        menuSair.setText("Sair   |");
        menuSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuSair);

        menuLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Login_37128.png"))); // NOI18N
        menuLogin.setText("Login");
        menuLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLoginMouseClicked(evt);
            }
        });
        menuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoginActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuLogin);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1656, Short.MAX_VALUE)
                    .addComponent(telaFundo))
                .addGap(7, 7, 7)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(telaFundo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        menuSairActionPerformed(evt);
        
    }//GEN-LAST:event_menuSairMouseClicked

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
    SessionManager.logout();
    limparUsuario();
    }//GEN-LAST:event_menuSairActionPerformed

    private void limparUsuario() {
    this.username = null;
    labelUsername.setText("Nome");
    atualizarMenu();
}
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        TelaReserva reserva = new TelaReserva();
        telaFundo.add(reserva);
        reserva.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void menuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoginActionPerformed
    
    }//GEN-LAST:event_menuLoginActionPerformed

    private void menuLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLoginMouseClicked
                     // Criar ou obter uma instância de LoginService
    LoginService loginService = new LoginService();
    
    // Passar a instância de LoginService ao construtor de TelaLogin
    TelaLogin tela = new TelaLogin(loginService);
    
    // Tornar a tela visível
    tela.setVisible(true);
    }//GEN-LAST:event_menuLoginMouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // Criar ou obter uma instância de LoginService
    LoginService loginService = new LoginService();
        ReservaGuest guest= new ReservaGuest(loginService);
        telaFundo.add(guest);
        guest.setVisible(true);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
       TelaReserva reserva = new TelaReserva();
        telaFundo.add(reserva);
        reserva.setVisible(true);
    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
          // Criar ou obter uma instância de LoginService
    LoginService loginService = new LoginService();
        ConsultaReserva guest= new ConsultaReserva(loginService);
        telaFundo.add(guest);
        guest.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        TelaReservaCliente reserva= new TelaReservaCliente();
        telaFundo.add(reserva);
        reserva.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConsHotel cons= new ConsHotel();
        telaFundo.add(cons);
        cons.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        TelaHotel tela=new TelaHotel();
        telaFundo.add(tela);
        tela.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Utilizador utilizador = new Utilizador();
        telaFundo.add(utilizador);
        utilizador.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuCadQuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadQuartosActionPerformed
        TelaAcomodacao telaacomodacao = new TelaAcomodacao();
        telaFundo.add(telaacomodacao);
        telaacomodacao.setVisible(true);
    }//GEN-LAST:event_menuCadQuartosActionPerformed

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked
        
    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        LoginService loginService = new LoginService();
        ConsultaAcomodacoes consulta= new ConsultaAcomodacoes(loginService); 
        telaFundo.add(consulta);
        consulta.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        LoginService loginService = new LoginService();
        ConsultaReservaCliente cons = new ConsultaReservaCliente(loginService);
        telaFundo.add(cons);
        cons.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        LoginService loginService = new LoginService();
        ConsultaReservaHotel cons = new ConsultaReservaHotel(loginService);
        telaFundo.add(cons);
        cons.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel labeltelafundo;
    private javax.swing.JMenuItem menuCadHospede;
    private javax.swing.JMenuItem menuCadQuartos;
    private javax.swing.JMenuItem menuConsHospede;
    private javax.swing.JMenuItem menuConsQuartos;
    private javax.swing.JMenu menuLogin;
    private javax.swing.JMenu menuSair;
    private javax.swing.JDesktopPane telaFundo;
    // End of variables declaration//GEN-END:variables

    private void menuSairActionPerformed(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
