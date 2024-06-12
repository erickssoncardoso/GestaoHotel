package services;

import dao.UtilizadoresDAO;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

/**
 * Serviços para TelaPrincipal.
 */
public class TelaPrincipalServico {

    private UtilizadoresDAO utilizadoresDAO;

    public TelaPrincipalServico() {
        this.utilizadoresDAO = new UtilizadoresDAO();
    }

    public void atualizarMenu(String username, JMenuBar menuBar, JMenuItem menuCadHospede, JMenuItem menuCadQuartos,JMenuItem MenuRegistHotel, JMenuItem MenuCadastUser ) {
        if (utilizadoresDAO.isCliente(username)) {
            desativarMenusCliente(menuBar, menuCadHospede, menuCadQuartos, MenuRegistHotel,MenuCadastUser);
        } else if (utilizadoresDAO.isFuncionario(username)) {
            ativarMenusFuncionario(menuCadHospede, menuCadQuartos);
        } else {
            menuBar.setVisible(false);
        }
    }

    private void desativarMenusCliente(JMenuBar menuBar, JMenuItem menuCadHospede, JMenuItem menuCadQuartos, JMenuItem MenuRegistHotel,JMenuItem MenuCadastUser) {
        menuCadHospede.setEnabled(false);
        menuCadQuartos.setEnabled(false);
        MenuRegistHotel.setEnabled(false);
        MenuCadastUser.setEnabled(false);
        
        for (int i = 1; i < menuBar.getMenuCount(); i++) {
            menuBar.getMenu(i).setVisible(false);
        }
    }

    private void ativarMenusFuncionario(JMenuItem menuCadHospede, JMenuItem menuCadQuartos) {
        menuCadHospede.setEnabled(true);
        menuCadQuartos.setEnabled(true);
    }

    public void iniciarRelogio(JLabel labelHora) {
        Thread clockThread = new Thread(() -> {
            try {
                while (true) {
                    LocalTime currentTime = LocalTime.now();
                    String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    labelHora.setText("Horário de Sistema: " + formattedTime);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        clockThread.start();
    }
}
