import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaAlteracao extends JFrame {
    //Criando meus atributos globais
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;

    private boolean atualizacaoValida;

    public TelaAlteracao() {
        setResizable(false);
        setTitle("Adega Virtual - Alteração");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 440, 370);
        setLocationRelativeTo(null);

        tela = Tema.painel();
        setContentPane(tela);

        JLabel lblTitulo = Tema.titulo("Alterar Dados", 28);
        lblTitulo.setBounds(0, 16, 424, 40);
        tela.add(lblTitulo);

        JLabel lblSub = Tema.subtitulo("Informe os campos para alteração");
        lblSub.setBounds(0, 56, 424, 20);
        tela.add(lblSub);

        tela.add(Tema.linha(40, 86, 344));

        JLabel lblNome = Tema.rotulo("Nome");
        lblNome.setBounds(40, 110, 110, 20);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(160, 108, 224, 26);
        Tema.campo(txtNome);
        tela.add(txtNome);

        JLabel lblSenhaAtual = Tema.rotulo("Senha atual");
        lblSenhaAtual.setBounds(40, 148, 110, 20);
        tela.add(lblSenhaAtual);

        passAtual = new JPasswordField();
        passAtual.setBounds(160, 146, 224, 26);
        Tema.campo(passAtual);
        tela.add(passAtual);

        JLabel lblNovaSenha = Tema.rotulo("Nova senha");
        lblNovaSenha.setBounds(40, 186, 110, 20);
        tela.add(lblNovaSenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(160, 184, 224, 26);
        Tema.campo(passSenha);
        tela.add(passSenha);

        JLabel lblConfSenha = Tema.rotulo("Confirmar senha");
        lblConfSenha.setBounds(40, 224, 120, 20);
        tela.add(lblConfSenha);

        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(160, 222, 224, 26);
        Tema.campo(confPassSenha);
        tela.add(confPassSenha);

        JButton btnCancelar = Tema.botao("Cancelar");
        btnCancelar.setBounds(40, 275, 150, 34);
        tela.add(btnCancelar);

        JButton btnAlterar = Tema.botao("Alterar");
        btnAlterar.setBounds(234, 275, 150, 34);
        tela.add(btnAlterar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });

        //Botão de alteração
        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                //Instancio a classe usuario
                Usuario usu = new Usuario();

                String senhaAtual = new String(passAtual.getPassword());
                String novaSenha = new String(passSenha.getPassword());
                String confSenha = new String(confPassSenha.getPassword());

                //Validações antes de efetivar a alteração
                //setando a senha e usuario
                usu.setSenha(novaSenha);
                usu.setUsuario(Usuario.usuarioSistema);

                if ("".equals(txtNome.getText())) {
                    //Nome vazio
                    JOptionPane.showMessageDialog(null,
                            "Campo nome do usuário precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    txtNome.grabFocus();

                } else if ("".equals(novaSenha)) {
                    //Senha vazia
                    JOptionPane.showMessageDialog(null,
                            "Campo senha precisa ser informado!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();

                } else if (usu.verificaUsuario(usu.getUsuario(), senhaAtual) == false) {
                    //Senha atual não confere
                    JOptionPane.showMessageDialog(null,
                            "Senha inválida, verifique!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passAtual.grabFocus();

                } else if (!novaSenha.equals(confSenha)) {
                    JOptionPane.showMessageDialog(null,
                            "Campos de Senha e Confirmação não são iguais!",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    passSenha.grabFocus();

                } else {
                    //Efetivo a alteração do usuario
                    atualizacaoValida
                            = usu.alteraUsuario(txtNome.getText(),
                                    usu.getUsuario(),
                                    novaSenha);

                    if (atualizacaoValida == true) {
                        JOptionPane.showMessageDialog(null,
                                "Dado(s) do usuário alterado(s), retornaremos "
                                + "a tela de login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);

                        //Voltamos para a tela de login
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao alterar o usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao alterar usuário " + ec.getMessage());
            }
        });
    }
}
