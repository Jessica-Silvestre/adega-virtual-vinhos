import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TelaVinhos extends JFrame {
    private JTextField txtNome, txtPais, txtAno, txtPreco, txtQtd;
    private JComboBox<String> cmbTipo;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel lblTotal;

    public TelaVinhos() {
        setResizable(false);
        setTitle("Adega Virtual - Meus Vinhos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 820, 560);
        setLocationRelativeTo(null);

        setContentPane(Tema.painel());
        getContentPane().setLayout(null);

        JLabel titulo = Tema.titulo("Minha Adega", 28);
        titulo.setBounds(0, 8, 804, 36);
        getContentPane().add(titulo);
        getContentPane().add(Tema.linha(20, 48, 764));

        //Linha 1: nome, tipo e país
        JLabel lblNome = Tema.rotulo("Nome do vinho");
        lblNome.setBounds(20, 58, 200, 16);
        getContentPane().add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(20, 76, 250, 26);
        Tema.campo(txtNome);
        getContentPane().add(txtNome);

        JLabel lblTipo = Tema.rotulo("Tipo");
        lblTipo.setBounds(290, 58, 100, 16);
        getContentPane().add(lblTipo);
        cmbTipo = new JComboBox<>(new String[]{"Tinto", "Branco", "Rosé",
            "Espumante", "Sobremesa"});
        cmbTipo.setBounds(290, 76, 120, 26);
        cmbTipo.setBackground(Tema.CREME);
        cmbTipo.setForeground(Tema.VINHO_ESCURO);
        getContentPane().add(cmbTipo);

        JLabel lblPais = Tema.rotulo("País de origem");
        lblPais.setBounds(430, 58, 150, 16);
        getContentPane().add(lblPais);
        txtPais = new JTextField();
        txtPais.setBounds(430, 76, 180, 26);
        Tema.campo(txtPais);
        getContentPane().add(txtPais);

        //Linha 2: ano, preço, quantidade e botões
        JLabel lblAno = Tema.rotulo("Ano (safra)");
        lblAno.setBounds(20, 112, 100, 16);
        getContentPane().add(lblAno);
        txtAno = new JTextField();
        txtAno.setBounds(20, 130, 90, 26);
        Tema.campo(txtAno);
        getContentPane().add(txtAno);

        JLabel lblPreco = Tema.rotulo("Preço (R$)");
        lblPreco.setBounds(130, 112, 100, 16);
        getContentPane().add(lblPreco);
        txtPreco = new JTextField();
        txtPreco.setBounds(130, 130, 110, 26);
        Tema.campo(txtPreco);
        getContentPane().add(txtPreco);

        JLabel lblQtd = Tema.rotulo("Quantidade");
        lblQtd.setBounds(260, 112, 100, 16);
        getContentPane().add(lblQtd);
        txtQtd = new JTextField();
        txtQtd.setBounds(260, 130, 100, 26);
        Tema.campo(txtQtd);
        getContentPane().add(txtQtd);

        JButton btnCadastrar = Tema.botao("Cadastrar");
        btnCadastrar.setBounds(430, 128, 110, 30);
        getContentPane().add(btnCadastrar);

        JButton btnLimpar = Tema.botao("Limpar");
        btnLimpar.setBounds(550, 128, 90, 30);
        getContentPane().add(btnLimpar);

        JButton btnExcluir = Tema.botao("Excluir selecionado");
        btnExcluir.setBounds(650, 128, 134, 30);
        getContentPane().add(btnExcluir);

        //Listagem (JTable)
        modelo = new DefaultTableModel(new Object[]{"Nome", "Tipo",
            "País", "Ano", "Preço (R$)", "Qtd"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modelo);
        tabela.setRowHeight(24);
        tabela.setBackground(Tema.CREME);
        tabela.setForeground(Tema.VINHO_ESCURO);
        tabela.setSelectionBackground(Tema.DOURADO);
        tabela.setSelectionForeground(Tema.VINHO_ESCURO);
        tabela.setGridColor(new Color(220, 200, 170));

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setBackground(Tema.VINHO_ESCURO);
        cabecalho.setForeground(Tema.DOURADO);
        cabecalho.setFont(new Font("SansSerif", Font.BOLD, 12));

        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBounds(20, 175, 764, 290);
        rolagem.getViewport().setBackground(Tema.CREME);
        rolagem.setBorder(BorderFactory.createLineBorder(Tema.DOURADO));
        getContentPane().add(rolagem);

        lblTotal = Tema.rotulo("");
        lblTotal.setBounds(20, 478, 500, 20);
        getContentPane().add(lblTotal);

        JButton btnVoltar = Tema.botao("Voltar");
        btnVoltar.setBounds(664, 474, 120, 30);
        getContentPane().add(btnVoltar);

        //Ações dos botões
        btnCadastrar.addActionListener((ActionEvent e) -> cadastrar());
        btnLimpar.addActionListener((ActionEvent e) -> limpar());
        btnExcluir.addActionListener((ActionEvent e) -> excluir());
        btnVoltar.addActionListener((ActionEvent e) -> {
            TelaInicio telaInicio = new TelaInicio();
            telaInicio.abreTela();
            dispose();
        });

        //Mostra os vinhos já cadastrados ao abrir a tela
        carregaTabela();
    }

    //Preenche a tabela com os vinhos do banco
    private void carregaTabela() {
        modelo.setRowCount(0);
        ArrayList<Vinho> lista = new Vinho().listaVinhos();
        int garrafas = 0;
        Locale br = Locale.forLanguageTag("pt-BR");

        for (Vinho v : lista) {
            modelo.addRow(new Object[]{
                v.getNome(),
                v.getTipo(),
                v.getPaisOrigem(),
                v.getAno(),
                String.format(br, "%.2f", v.getPreco()),
                v.getQuantidade()
            });
            garrafas += v.getQuantidade();
        }
        lblTotal.setText("Rótulos cadastrados: " + lista.size()
                + "   |   Garrafas em estoque: " + garrafas);
    }

    private void cadastrar() {
        String nome = txtNome.getText().trim();
        String pais = txtPais.getText().trim();
        int ano;
        double preco;
        int qtd;

        if (nome.isEmpty()) {
            erro("Campo nome do vinho precisa ser informado!");
            txtNome.grabFocus();
            return;
        }
        if (pais.isEmpty()) {
            erro("Campo país de origem precisa ser informado!");
            txtPais.grabFocus();
            return;
        }
        try {
            ano = Integer.parseInt(txtAno.getText().trim());
        } catch (NumberFormatException ex) {
            erro("Ano inválido! Digite apenas números (ex.: 2019).");
            txtAno.grabFocus();
            return;
        }
        if (ano < 1800 || ano > 2100) {
            erro("Ano fora do intervalo permitido!");
            txtAno.grabFocus();
            return;
        }
        try {
            preco = Double.parseDouble(txtPreco.getText().trim().replace(",", "."));
        } catch (NumberFormatException ex) {
            erro("Preço inválido! Exemplo: 89,90");
            txtPreco.grabFocus();
            return;
        }
        if (preco < 0) {
            erro("O preço não pode ser negativo!");
            txtPreco.grabFocus();
            return;
        }
        try {
            qtd = Integer.parseInt(txtQtd.getText().trim());
        } catch (NumberFormatException ex) {
            erro("Quantidade inválida! Digite apenas números.");
            txtQtd.grabFocus();
            return;
        }
        if (qtd < 0) {
            erro("A quantidade não pode ser negativa!");
            txtQtd.grabFocus();
            return;
        }

        Vinho v = new Vinho();
        if (v.existeVinho(nome)) {
            erro("Já existe um vinho cadastrado com este nome!");
            txtNome.grabFocus();
            return;
        }

        v.setNome(nome);
        v.setTipo((String) cmbTipo.getSelectedItem());
        v.setPaisOrigem(pais);
        v.setAno(ano);
        v.setPreco(preco);
        v.setQuantidade(qtd);

        if (v.cadastraVinho()) {
            JOptionPane.showMessageDialog(null, "Vinho cadastrado com sucesso.",
                    "Atenção", JOptionPane.INFORMATION_MESSAGE);
            limpar();
            carregaTabela();
        } else {
            erro("Problemas ao cadastrar o vinho.");
        }
    }

    private void excluir() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            erro("Selecione um vinho na tabela para excluir!");
            return;
        }
        String nome = (String) modelo.getValueAt(linha, 0);
        int opcao = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o vinho \"" + nome + "\"?",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            if (new Vinho().excluiVinho(nome)) {
                carregaTabela();
            } else {
                erro("Problemas ao excluir o vinho.");
            }
        }
    }

    private void limpar() {
        txtNome.setText("");
        txtPais.setText("");
        txtAno.setText("");
        txtPreco.setText("");
        txtQtd.setText("");
        cmbTipo.setSelectedIndex(0);
        txtNome.grabFocus();
    }

    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Atenção",
                JOptionPane.ERROR_MESSAGE);
    }
}