
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Cores e estilos do tema "Adega Virtual" (mundo do vinho).
 */
public class Tema {
    public static final Color VINHO = new Color(92, 18, 40);
    public static final Color VINHO_ESCURO = new Color(55, 8, 22);
    public static final Color DOURADO = new Color(201, 162, 75);
    public static final Color CREME = new Color(245, 230, 211);

    //Painel de fundo das telas
    public static JPanel painel() {
        JPanel p = new JPanel();
        p.setBackground(VINHO);
        p.setLayout(null);
        return p;
    }

    //Faixa dourada decorativa
    public static JPanel linha(int x, int y, int largura) {
        JPanel l = new JPanel();
        l.setBackground(DOURADO);
        l.setBounds(x, y, largura, 2);
        return l;
    }

    //Título grande em dourado
    public static JLabel titulo(String texto, int tamanho) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, tamanho));
        l.setForeground(DOURADO);
        return l;
    }

    //Texto de apoio em creme
    public static JLabel subtitulo(String texto) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(new Font("Serif", Font.ITALIC, 14));
        l.setForeground(CREME);
        return l;
    }

    //Rótulo de campo
    public static JLabel rotulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        l.setForeground(CREME);
        return l;
    }

    //Estilo para JTextField e JPasswordField
    public static void campo(JTextField c) {
        c.setBackground(CREME);
        c.setForeground(VINHO_ESCURO);
        c.setCaretColor(VINHO_ESCURO);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DOURADO),
                BorderFactory.createEmptyBorder(0, 4, 0, 4)));
    }

    //Botão dourado
    public static JButton botao(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBackground(DOURADO);
        b.setForeground(VINHO_ESCURO);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(true);
        b.setBorder(BorderFactory.createLineBorder(VINHO_ESCURO, 1));
        return b;
    }
}