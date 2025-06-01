package phealth.GUI.poo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GerenciadorProntuario {
    private static final String ARQUIVO_PRONTUARIOS = "prontuario.txt";

    public static void salvarProntuario(JFrame janelaPai, Prontuario prontuario, Runnable limparCampos) {
        if (prontuario.getNomePaciente().isEmpty() ||
                prontuario.getCpfPaciente().isEmpty() ||
                prontuario.getPeso().isEmpty() ||
                prontuario.getCid().isEmpty() ||
                prontuario.getNomeMedico().isEmpty() ||
                prontuario.getCrmMedico().isEmpty() ||
                prontuario.getEspecialidade().isEmpty() ||
                prontuario.getNumeroConsultorio().isEmpty() ||
                prontuario.getDataConsulta().isEmpty() ||
                prontuario.getEvolucoes().isEmpty() ||
                prontuario.getPrescricaoMedicamentos().isEmpty() ||
                prontuario.getPrescricaoMateriais().isEmpty() ||
                prontuario.getProcedimentos().isEmpty() ||
                prontuario.getAlergiaMedicamentos().isEmpty()) {

            JOptionPane.showMessageDialog(janelaPai,
                    "Por favor, preencha todos os campos obrigatórios!",
                    "Campos Inválidos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(prontuario.getPeso().replace(',', '.'));
            if (peso <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(janelaPai,
                    "Peso inválido! Informe um número maior que zero (ex: 70.5).",
                    "Erro de Formato",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate.parse(prontuario.getDataConsulta());
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(janelaPai,
                    "Data de Consulta inválida!\nUse o formato YYYY-MM-DD.",
                    "Data Inválida",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numeroProntuario = obterProximoNumeroProntuario();
        try (FileWriter fw = new FileWriter(ARQUIVO_PRONTUARIOS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            pw.print(prontuario.formatarParaArquivo(numeroProntuario));
            JOptionPane.showMessageDialog(janelaPai,
                    "Prontuário #" + numeroProntuario + " salvo com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos.run();

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(janelaPai,
                    "Erro ao salvar o prontuário:\n" + ioe.getMessage(),
                    "Erro de I/O",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void exibirProntuarios(JFrame janelaPai, Color corFundo, Color corPrincipal, Color corTexto) {
        File arquivo = new File(ARQUIVO_PRONTUARIOS);
        if (!arquivo.exists() || arquivo.length() == 0) {
            JOptionPane.showMessageDialog(janelaPai,
                    "Nenhum prontuário encontrado para exibir.",
                    "Arquivo Vazio",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder conteudo = new StringBuilder();
        try (FileReader fr = new FileReader(arquivo);
             BufferedReader br = new BufferedReader(fr)) {

            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }

        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(janelaPai,
                    "Erro ao ler o arquivo de prontuários:\n" + ioe.getMessage(),
                    "Erro de I/O",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame janelaExibir = new JFrame("Prontuários Salvos");
        janelaExibir.setSize(650, 750);
        janelaExibir.setLocationRelativeTo(janelaPai);
        janelaExibir.getContentPane().setBackground(corFundo);
        janelaExibir.setMinimumSize(new Dimension(600, 600));

        JTextArea areaConteudo = new JTextArea();
        areaConteudo.setEditable(false);
        areaConteudo.setBackground(corFundo);
        areaConteudo.setForeground(corTexto);
        areaConteudo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaConteudo.setText(conteudo.toString());
        areaConteudo.setCaretPosition(0);
        areaConteudo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corPrincipal, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JScrollPane painelRolagem = new JScrollPane(areaConteudo,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelRolagem.setBorder(BorderFactory.createEmptyBorder());
        painelRolagem.getViewport().setBackground(corFundo);
        painelRolagem.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = corPrincipal;
                this.trackColor = corFundo;
            }
        });

        janelaExibir.getContentPane().add(painelRolagem);
        janelaExibir.setVisible(true);
    }

    private static int obterProximoNumeroProntuario() {
        File arquivo = new File(ARQUIVO_PRONTUARIOS);
        if (!arquivo.exists()) {
            return 1;
        }

        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Número do Prontuário:")) {
                    contador++;
                }
            }
        } catch (IOException e) {
            return 1;
        }
        return contador + 1;
    }
}