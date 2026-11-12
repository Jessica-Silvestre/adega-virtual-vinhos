import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastro extends JFrame {
    //Declaração dos atributos de tela
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;

    //Validações de usuário e cadastro corretos
    private boolean usuarioValido;
    private boolean cadastroValido;

    //String de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0;

    //Método construtor da classe
    public TelaCadastro() {
        setResizable(false);
        setTitle("Adega Virtual - Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 440, 370);
        setLocationRelativeTo(null);

        tela = Tema.painel();
        setContentPane(tela);

        JLabel lblTitulo = Tema.titulo("Novo Cadastro", 28);
        lblTitulo.setBounds(0, 16, 424, 40);
        tela.add(lblTitulo);

        JLabel lblSub = Tema.subtitulo("Informe os campos para entrar na adega");
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

        JLabel lblUsuario = Tema.rotulo("Usuário");
        lblUsuario.setBounds(40, 148, 110, 20);
        tela.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(160, 146, 224, 26);
        Tema.campo(txtUsuario);
        tela.add(txtUsuario);

        JLabel lblSenha = Tema.rotulo("Senha");
        lblSenha.setBounds(40, 186, 110, 20);
        tela.add(lblSenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(160, 184, 224, 26);
        Tema.campo(passSenha);
        tela.add(passSenha);

        JLabel lblConfSenha = Tema.rotulo("Confirmar senha");
        lblConfSenha.setBounds(40, 224, 120, 20);
        tela.add(lblConfSenha);

        passConfSenha = new JPasswordField();
        passConfSenha.setBounds(160, 222, 224, 26);
        Tema.campo(passConfSenha);
        tela.add(passConfSenha);

        JButton btnCancelar = Tema.botao("Cancelar");
        btnCancelar.setBounds(40, 275, 150, 34);
        tela.add(btnCancelar);

        JButton btnCadastrar = Tema.botao("Cadastrar");
        btnCadastrar.setBounds(234, 275, 150, 34);
        tela.add(btnCadastrar);

        //Ação do botão cancelar: volta para a tela de login
        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
            dispose();
        });

        //Ação do botão cadastrar usuário na base de dados
        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                //Instancio o objeto usuario
                Usuario usu = new Usuario();

                //Realizando os setters dos dados de tela
                usu.setNome(txtNome.getText());
                usu.setUsuario(txtUsuario.getText());
                usu.setSenha(new String(passSenha.getPassword()));

                String confirma = new String(passConfSenha.getPassword());

                //Validações de preenchimento dos dados
                if ("".equals(usu.getNome())) {
                    mensagemJOption = "Campo nome do usuário precisa ser informado";
                    mensagemTipo = 0;
                } else if ("".equals(usu.getUsuario())) {
                    mensagemJOption = "Campo usuário precisa ser informado";
                    mensagemTipo = 0;
                } else if (!usu.getSenha().equals(confirma)) {
                    mensagemJOption = "Campos senha e confirmação de senha não coincidem!";
                    mensagemTipo = 0;
                } else if ("".equals(usu.getSenha())) {
                    mensagemJOption = "A senha precisa ser informada!";
                    mensagemTipo = 0;
                } else {
                    //Verifico somente o usuário no banco (sobrecarga de método)
                    usuarioValido = usu.verificaUsuario(usu.getUsuario());

                    if (usuarioValido == true) {
                        mensagemJOption = "Usuário já existente na base de dados.";
                        mensagemTipo = 0;
                    } else {
                        cadastroValido = usu.cadastraUsuario(usu.getNome(),
                                usu.getUsuario(), usu.getSenha());

                        if (cadastroValido == true) {
                            //Usuário cadastrado na base de dados
                            mensagemJOption = "Usuário cadastrado com sucesso.";
                            mensagemTipo = 1;
                        } else {
                            //Algum erro aconteceu
                            mensagemJOption = "Problemas ao inserir o usuário.";
                            mensagemTipo = 0;
                        }
                    }
                }

                //Mostra a mensagem referida
                JOptionPane.showMessageDialog(null, mensagemJOption,
                        "Atenção", mensagemTipo);

                if (mensagemTipo == 1) {
                    //Voltamos para a tela de login
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    dispose();
                }

            } catch (HeadlessException ec) {
                System.out.println("Erro no cadastro do usuário");
            }
        });
    }

    public void abreTela() {
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }
}