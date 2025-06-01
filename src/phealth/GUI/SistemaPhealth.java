package phealth.GUI;

import phealth.GUI.poo.GerenciadorProntuario;
import phealth.GUI.poo.Prontuario;
import phealth.GUI.poo.UtilEstilo;

import javax.swing.*;
import java.awt.*;

public class SistemaPhealth extends JFrame {


    private static final Color COR_FUNDO = Color.WHITE;
    private static final Color COR_PRINCIPAL = new Color(0, 0, 128); // Azul-marinho
    private static final Color COR_TEXTO = Color.BLACK;


    private JTextField campoNomePaciente, campoCpfPaciente, campoPeso, campoCid, campoNomeMedico, campoCrmMedico, campoEspecialidade, campoNumeroConsultorio, campoDataConsulta, campoAlergiaMedicamentos;
    private JComboBox<String> comboTipoSanguineo;
    private JTextArea areaEvolucoes, areaPrescricaoMedicamentos, areaPrescricaoMateriais, areaProcedimentos;
    private JButton botaoSalvar, botaoLimpar, botaoExibir;

    public SistemaPhealth() {
        super("Phealth EMR - Sistema de Gestão Hospitalar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 800);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 700));


        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(painelPrincipal);


        JPanel painelCabecalho = UtilEstilo.criarPainelCabecalho("Phealth EMR", COR_PRINCIPAL, COR_FUNDO);
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);


        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(COR_FUNDO);
        painelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_PRINCIPAL, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;


        campoNomePaciente = UtilEstilo.criarCampoTexto(25);
        adicionarCampoFormulario(painelFormulario, gbc, "Nome do Paciente:", campoNomePaciente, y++);
        campoCpfPaciente = UtilEstilo.criarCampoTexto(15);
        adicionarCampoFormulario(painelFormulario, gbc, "CPF do Paciente:", campoCpfPaciente, y++);
        campoPeso = UtilEstilo.criarCampoTexto(5);
        adicionarCampoFormulario(painelFormulario, gbc, "Peso (kg):", campoPeso, y++);
        comboTipoSanguineo = UtilEstilo.criarComboBox(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        adicionarCampoFormulario(painelFormulario, gbc, "Tipo Sanguíneo:", comboTipoSanguineo, y++);
        campoCid = UtilEstilo.criarCampoTexto(10);
        adicionarCampoFormulario(painelFormulario, gbc, "CID:", campoCid, y++);
        campoNomeMedico = UtilEstilo.criarCampoTexto(25);
        adicionarCampoFormulario(painelFormulario, gbc, "Nome do Médico:", campoNomeMedico, y++);
        campoCrmMedico = UtilEstilo.criarCampoTexto(10);
        adicionarCampoFormulario(painelFormulario, gbc, "CRM do Médico:", campoCrmMedico, y++);
        campoEspecialidade = UtilEstilo.criarCampoTexto(20);
        adicionarCampoFormulario(painelFormulario, gbc, "Especialidade:", campoEspecialidade, y++);
        campoNumeroConsultorio = UtilEstilo.criarCampoTexto(5);
        adicionarCampoFormulario(painelFormulario, gbc, "Número do Consultório:", campoNumeroConsultorio, y++);
        campoDataConsulta = UtilEstilo.criarCampoTexto(10);
        adicionarCampoFormulario(painelFormulario, gbc, "Data da Consulta (YYYY-MM-DD):", campoDataConsulta, y++);

        areaEvolucoes = UtilEstilo.criarAreaTexto(5, 25);
        JScrollPane rolagemEvolucoes = UtilEstilo.criarPainelRolagem(areaEvolucoes);
        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.NORTHWEST;
        painelFormulario.add(UtilEstilo.criarRotulo("Evoluções:", COR_PRINCIPAL), gbc);
        gbc.gridx = 1;
        painelFormulario.add(rolagemEvolucoes, gbc);
        y++; gbc.anchor = GridBagConstraints.WEST;

        areaPrescricaoMedicamentos = UtilEstilo.criarAreaTexto(5, 25);
        JScrollPane rolagemPrescricaoMed = UtilEstilo.criarPainelRolagem(areaPrescricaoMedicamentos);
        adicionarCampoFormulario(painelFormulario, gbc, "Prescrição de Medicamentos:", rolagemPrescricaoMed, y++);

        areaPrescricaoMateriais = UtilEstilo.criarAreaTexto(5, 25);
        JScrollPane rolagemPrescricaoMat = UtilEstilo.criarPainelRolagem(areaPrescricaoMateriais);
        adicionarCampoFormulario(painelFormulario, gbc, "Prescrição de Materiais:", rolagemPrescricaoMat, y++);

        areaProcedimentos = UtilEstilo.criarAreaTexto(5, 25);
        JScrollPane rolagemProcedimentos = UtilEstilo.criarPainelRolagem(areaProcedimentos);
        adicionarCampoFormulario(painelFormulario, gbc, "Procedimentos:", rolagemProcedimentos, y++);

        campoAlergiaMedicamentos = UtilEstilo.criarCampoTexto(25);
        adicionarCampoFormulario(painelFormulario, gbc, "Alergia a Medicamentos (ou \"Nenhuma\"):", campoAlergiaMedicamentos, y++);


        JScrollPane rolagemFormulario = new JScrollPane(painelFormulario,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rolagemFormulario.getViewport().setBackground(COR_FUNDO);
        rolagemFormulario.setBorder(BorderFactory.createEmptyBorder());
        painelPrincipal.add(rolagemFormulario, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        painelBotoes.setBackground(COR_FUNDO);
        botaoSalvar = UtilEstilo.criarBotao("Salvar Prontuário", COR_PRINCIPAL, COR_TEXTO);
        botaoLimpar = UtilEstilo.criarBotao("Limpar Campos", COR_PRINCIPAL, COR_TEXTO);
        botaoExibir = UtilEstilo.criarBotao("Exibir Prontuários", COR_PRINCIPAL, COR_TEXTO);
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoExibir);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);


        botaoSalvar.addActionListener(e -> {
            Prontuario prontuario = new Prontuario(
                    campoNomePaciente.getText().trim(),
                    campoCpfPaciente.getText().trim(),
                    campoPeso.getText().trim(),
                    (String) comboTipoSanguineo.getSelectedItem(),
                    campoCid.getText().trim(),
                    campoNomeMedico.getText().trim(),
                    campoCrmMedico.getText().trim(),
                    campoEspecialidade.getText().trim(),
                    campoNumeroConsultorio.getText().trim(),
                    campoDataConsulta.getText().trim(),
                    areaEvolucoes.getText().trim(),
                    areaPrescricaoMedicamentos.getText().trim(),
                    areaPrescricaoMateriais.getText().trim(),
                    areaProcedimentos.getText().trim(),
                    campoAlergiaMedicamentos.getText().trim()
            );
            GerenciadorProntuario.salvarProntuario(this, prontuario, this::limparCampos);
        });
        botaoLimpar.addActionListener(e -> limparCampos());
        botaoExibir.addActionListener(e -> GerenciadorProntuario.exibirProntuarios(this, COR_FUNDO, COR_PRINCIPAL, COR_TEXTO));
    }

    private void adicionarCampoFormulario(JPanel painel, GridBagConstraints gbc, String rotulo, Component campo, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        painel.add(UtilEstilo.criarRotulo(rotulo, COR_PRINCIPAL), gbc);
        gbc.gridx = 1;
        painel.add(campo, gbc);
    }

    private void limparCampos() {
        campoNomePaciente.setText("");
        campoCpfPaciente.setText("");
        campoPeso.setText("");
        comboTipoSanguineo.setSelectedIndex(0);
        campoCid.setText("");
        campoNomeMedico.setText("");
        campoCrmMedico.setText("");
        campoEspecialidade.setText("");
        campoNumeroConsultorio.setText("");
        campoDataConsulta.setText("");
        areaEvolucoes.setText("");
        areaPrescricaoMedicamentos.setText("");
        areaPrescricaoMateriais.setText("");
        areaProcedimentos.setText("");
        campoAlergiaMedicamentos.setText("");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignorado) {
        }
        SwingUtilities.invokeLater(() -> new SistemaPhealth().setVisible(true));
    }
}