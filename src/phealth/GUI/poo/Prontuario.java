package phealth.GUI.poo;

public class Prontuario {
    private String nomePaciente, cpfPaciente, peso, tipoSanguineo, cid;
    private String nomeMedico, crmMedico, especialidade, numeroConsultorio, dataConsulta;
    private String evolucoes, prescricaoMedicamentos, prescricaoMateriais, procedimentos, alergiaMedicamentos;

    public Prontuario(String nomePaciente, String cpfPaciente, String peso, String tipoSanguineo, String cid,
                      String nomeMedico, String crmMedico, String especialidade, String numeroConsultorio,
                      String dataConsulta, String evolucoes, String prescricaoMedicamentos,
                      String prescricaoMateriais, String procedimentos, String alergiaMedicamentos) {
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
        this.peso = peso;
        this.tipoSanguineo = tipoSanguineo;
        this.cid = cid;
        this.nomeMedico = nomeMedico;
        this.crmMedico = crmMedico;
        this.especialidade = especialidade;
        this.numeroConsultorio = numeroConsultorio;
        this.dataConsulta = dataConsulta;
        this.evolucoes = evolucoes;
        this.prescricaoMedicamentos = prescricaoMedicamentos;
        this.prescricaoMateriais = prescricaoMateriais;
        this.procedimentos = procedimentos;
        this.alergiaMedicamentos = alergiaMedicamentos;
    }

    public String formatarParaArquivo(int numeroProntuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("===== Prontuário =====\n");
        sb.append("Número do Prontuário: ").append(numeroProntuario).append("\n");
        sb.append("Nome do Paciente: ").append(nomePaciente).append("\n");
        sb.append("CPF do Paciente: ").append(cpfPaciente).append("\n");
        sb.append("Peso: ").append(peso).append(" kg\n");
        sb.append("Tipo Sanguíneo: ").append(tipoSanguineo).append("\n");
        sb.append("CID: ").append(cid).append("\n");
        sb.append("---- Dados do Médico ----\n");
        sb.append("Nome do Médico: ").append(nomeMedico).append("\n");
        sb.append("CRM do Médico: ").append(crmMedico).append("\n");
        sb.append("Especialidade: ").append(especialidade).append("\n");
        sb.append("Número do Consultório: ").append(numeroConsultorio).append("\n");
        sb.append("Data da Consulta: ").append(dataConsulta).append("\n");
        sb.append("---- Evoluções ----\n").append(evolucoes).append("\n");
        sb.append("---- Prescrição de Medicamentos ----\n").append(prescricaoMedicamentos).append("\n");
        sb.append("---- Prescrição de Materiais ----\n").append(prescricaoMateriais).append("\n");
        sb.append("---- Procedimentos ----\n").append(procedimentos).append("\n");
        sb.append("Alergia a Medicamentos: ").append(alergiaMedicamentos).append("\n");
        sb.append("===============================\n\n");
        return sb.toString();
    }

    public String getNomePaciente() { return nomePaciente; }
    public String getCpfPaciente() { return cpfPaciente; }
    public String getPeso() { return peso; }
    public String getTipoSanguineo() { return tipoSanguineo; }
    public String getCid() { return cid; }
    public String getNomeMedico() { return nomeMedico; }
    public String getCrmMedico() { return crmMedico; }
    public String getEspecialidade() { return especialidade; }
    public String getNumeroConsultorio() { return numeroConsultorio; }
    public String getDataConsulta() { return dataConsulta; }
    public String getEvolucoes() { return evolucoes; }
    public String getPrescricaoMedicamentos() { return prescricaoMedicamentos; }
    public String getPrescricaoMateriais() { return prescricaoMateriais; }
    public String getProcedimentos() { return procedimentos; }
    public String getAlergiaMedicamentos() { return alergiaMedicamentos; }
}