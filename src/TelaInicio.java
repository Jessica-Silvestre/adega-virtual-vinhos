import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaInicio extends JFrame {
    private final JPanel tela;

    //Atributo que faremos para validar a Exclusão
    private boolean exclusaoValida;

    public TelaInicio() {
        setResizable(false);
        setTitle("Adega Virtual - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 480, 330);
        setLocationRelativeTo(null);

        tela = Tema.painel();
        setContentPane(tela);

        JLabel lblTitulo = Tema.titulo("Adega Virtual", 30);
        lblTitulo.setBounds(0, 16, 464, 40);
        tela.add(lblTitulo);

        tela.add(Tema.linha(40, 62, 384));

        //Concateno o Label com o atributo global nome da classe Usuario
        JLabel lblUsuario = new JLabel("Bem-vindo(a), " + Usuario.nomeUsuario,
                SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Serif", Font.PLAIN, 20));
        lblUsuario.setForeground(Tema.CREME);
        lblUsuario.setBounds(0, 80, 464, 30);
        tela.add(lblUsuario);

        JButton btnVinhos = Tema.botao("Meus Vinhos");
        btnVinhos.setBounds(80, 135, 304, 42);
        btnVinhos.setFont(new Font("SansSerif", Font.BOLD, 15));
        tela.add(btnVinhos);

        JButton btnAlterar = Tema.botao("Alterar Dados");
        btnAlterar.setBounds(20, 215, 130, 32);
        tela.add(btnAlterar);

        JButton btnExcluir = Tema.botao("Excluir Conta");
        btnExcluir.setBounds(165, 215, 130, 32);
        tela.add(btnExcluir);

        JButton btnVoltar = Tema.botao("Voltar");
        btnVoltar.setBounds(310, 215, 130, 32);
        tela.add(btnVoltar);

        btnVinhos.addActionListener((ActionEvent e) -> {
            try {
                TelaVinhos telaVinhos = new TelaVinhos();
                telaVinhos.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de vinhos "
                        + ec.getMessage());
            }
        });

        btnVoltar.addActionListener((ActionEvent e) -> {
            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de login "
                        + ec.getMessage());
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                TelaAlteracao telaAlterar = new TelaAlteracao();
                telaAlterar.setVisible(true);
                dispose();

            } catch (Exception ec) {
                System.out.println("Erro ao acessar a tela de alteração "
                        + ec.getMessage());
            }
        });

        btnExcluir.addActionListener((ActionEvent e) -> {
            try {
                //Array que recebe as opções
                Object[] options = {"Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null,
                        Usuario.nomeUsuario
                        + ", deseja se excluir?", "Atenção",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[0]);

                if (opcao == 0) {
                    //Excluir o usuario
                    //Instancio a classe usuario
                    Usuario usu = new Usuario();

                    exclusaoValida
                            = usu.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoValida == true) {
                        //Usuario excluído na base de dados
                        JOptionPane.showMessageDialog(null,
                                "Usuário deletado corretamente, voltaremos "
                                + "a tela de login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);

                        //Abrimos a tela de login novamente
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao excluir o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao excluir usuário "
                        + ec.getMessage());
            }
        });
    }

    public void abreTela() {
        TelaInicio telaInicio = new TelaInicio();
        telaInicio.setVisible(true);
    }
}