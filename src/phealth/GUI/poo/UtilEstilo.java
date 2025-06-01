package phealth.GUI.poo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UtilEstilo {
    private static final Color COR_ACENTO = new Color(30, 30, 150);

    public static JLabel criarRotulo(String texto, Color corPrincipal) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setForeground(corPrincipal);
        rotulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        return rotulo;
    }

    public static JTextField criarCampoTexto(int colunas) {
        JTextField campoTexto = new JTextField(colunas);
        campoTexto.setBackground(Color.WHITE);
        campoTexto.setForeground(Color.BLACK);
        campoTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 128), 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        campoTexto.setCaretColor(new Color(0, 0, 128));
        return campoTexto;
    }

    public static JComboBox<String> criarComboBox(String[] itens) {
        JComboBox<String> comboBox = new JComboBox<>(itens);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 128), 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return comboBox;
    }

    public static JTextArea criarAreaTexto(int linhas, int colunas) {
        JTextArea areaTexto = new JTextArea(linhas, colunas);
        areaTexto.setBackground(Color.WHITE);
        areaTexto.setForeground(Color.BLACK);
        areaTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setCaretColor(new Color(0, 0, 128));
        areaTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 128), 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return areaTexto;
    }

    public static JScrollPane criarPainelRolagem(JTextArea areaTexto) {
        JScrollPane painelRolagem = new JScrollPane(areaTexto,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelRolagem.setBackground(Color.WHITE);
        painelRolagem.getViewport().setBackground(Color.WHITE);
        painelRolagem.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 128), 1, true));
        painelRolagem.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0, 0, 128);
                this.trackColor = Color.WHITE;
            }
        });
        return painelRolagem;
    }

    public static JButton criarBotao(String texto, Color corPrincipal, Color corTexto) {
        JButton botao = new JButton(texto);
        botao.setBackground(corPrincipal);
        botao.setForeground(corTexto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corPrincipal.darker(), 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        botao.setPreferredSize(new Dimension(180, 40));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(COR_ACENTO);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(corPrincipal);
            }
        });
        return botao;
    }

    public static JPanel criarPainelCabecalho(String titulo, Color corPrincipal, Color corTexto) {
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, corPrincipal, 0, getHeight(), COR_ACENTO);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painel.setPreferredSize(new Dimension(0, 70));
        JLabel rotulo = new JLabel(titulo);
        rotulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        rotulo.setForeground(corTexto);
        painel.add(rotulo);
        return painel;
    }
}