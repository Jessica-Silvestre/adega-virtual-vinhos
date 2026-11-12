import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    //tela Objeto JPanel (tela em si)
    private final JPanel panelTela;

    //txtUsuario Objeto JTextField (campo na tela)
    private final JTextField txtUsuario;

    //pswSenha Objeto JPasswordField (campo na tela)
    private final JPasswordField pswSenha;

    //validar se o usuario é correto
    private boolean usuarioValido;

    //Método construtor
    public TelaLogin() {
        setResizable(false);
        setTitle("Adega Virtual - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 440, 320);
        setLocationRelativeTo(null);

        panelTela = Tema.painel();
        setContentPane(panelTela);

        JLabel lblTitulo = Tema.titulo("Adega Virtual", 32);
        lblTitulo.setBounds(0, 18, 424, 44);
        panelTela.add(lblTitulo);

        JLabel lblSub = Tema.subtitulo("Sua coleção de vinhos, sempre à mão");
        lblSub.setBounds(0, 62, 424, 20);
        panelTela.add(lblSub);

        panelTela.add(Tema.linha(50, 92, 324));

        JLabel lblUsuario = Tema.rotulo("Usuário");
        lblUsuario.setBounds(50, 120, 70, 20);
        panelTela.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 118, 244, 26);
        Tema.campo(txtUsuario);
        panelTela.add(txtUsuario);

        JLabel lblSenha = Tema.rotulo("Senha");
        lblSenha.setBounds(50, 160, 70, 20);
        panelTela.add(lblSenha);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(130, 158, 244, 26);
        Tema.campo(pswSenha);
        panelTela.add(pswSenha);

        JButton btnCadastrar = Tema.botao("Cadastrar");
        btnCadastrar.setBounds(50, 215, 150, 34);
        panelTela.add(btnCadastrar);

        JButton btnEntrar = Tema.botao("Entrar");
        btnEntrar.setBounds(224, 215, 150, 34);
        panelTela.add(btnEntrar);

        //Enter no teclado aciona o botão Entrar
        getRootPane().setDefaultButton(btnEntrar);

        //Ação no botão para cadastrar Usuário
        btnCadastrar.addActionListener((ActionEvent e) -> {
            //Instancio a classe TelaCadastro
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.abreTela();
            dispose();
        });

        //Ação no botão de entrar no sistema
        btnEntrar.addActionListener((ActionEvent e) -> {
            //Instancio a classe usuario
            Usuario usu = new Usuario();

            //Utilizo o setter de usuario e senha
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(new String(pswSenha.getPassword()));

            if ("".equals(txtUsuario.getText())) {
                //Vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo usuário precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                //Voltar o cursor para o campo txtUsuario
                txtUsuario.grabFocus();
            } else if ("".equals(new String(pswSenha.getPassword()))) {
                //Vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                //Voltar o cursor para o campo pswSenha
                pswSenha.grabFocus();
            } else {
                //Verifico se o usuário consta no banco de dados
                usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                        usu.getSenha());

                if (usuarioValido == true) {
                    //Usuario e senha bateram no banco, estão corretos
                    JOptionPane.showMessageDialog(null,
                            "Usuário válido em nossa base de dados",
                            "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);

                    TelaInicio telaInicio = new TelaInicio();
                    telaInicio.abreTela();

                    dispose();
                } else {
                    //Usuario e senha não bateram no banco, estão incorretos
                    JOptionPane.showMessageDialog(null,
                            "Usuário inválido ou inexistente",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);

                    //Método para limpar os textos
                    limpaText();

                    //Mando o foco para o campo usuário
                    txtUsuario.grabFocus();
                }
            }
        });
    }

    public void abreTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }

    public void limpaText() {
        txtUsuario.setText("");
        pswSenha.setText("");
    }
}