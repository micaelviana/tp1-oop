public class Projeto {
    int id;
    String nome;
    String professor;
    
    private double despesasCapitalPrevisto;
    private double despesasCapitalGasto;
    private double materialConsumoPrevisto;
    private double materialConsumoGasto;
    private double servicosPessoaFisicaPrevisto;
    private double servicosPessoaFisicaGasto;
    private double servicosPessoaJuridicaPrevisto;
    private double servicosPessoaJuridicaGasto;
    private double diariasPrevisto;
    private double diariasGasto;
    private double passagensPrevisto;
    private double passagensGasto;

    public Projeto(){}

    public Projeto(String nome, String professor, double despesasCapitalPrevisto, double despesasCapitalGasto, double materialConsumoPrevisto, double materialConsumoGasto, double servicosPessoaFisicaPrevisto, double servicosPessoaFisicaGasto, double servicosPessoaJuridicaPrevisto, double servicosPessoaJuridicaGasto, double diariasPrevisto, double diariasGasto, double passagensPrevisto, double passagensGasto) {
        this.nome = nome;
        this.professor = professor;
        this.despesasCapitalPrevisto = despesasCapitalPrevisto;
        this.despesasCapitalGasto = despesasCapitalGasto;
        this.materialConsumoPrevisto = materialConsumoPrevisto;
        this.materialConsumoGasto = materialConsumoGasto;
        this.servicosPessoaFisicaPrevisto = servicosPessoaFisicaPrevisto;
        this.servicosPessoaFisicaGasto = servicosPessoaFisicaGasto;
        this.servicosPessoaJuridicaPrevisto = servicosPessoaJuridicaPrevisto;
        this.servicosPessoaJuridicaGasto = servicosPessoaJuridicaGasto;
        this.diariasPrevisto = diariasPrevisto;
        this.diariasGasto = diariasGasto;
        this.passagensPrevisto = passagensPrevisto;
        this.passagensGasto = passagensGasto;
    }

    //outro construtor, os valores gastos nao vao ser colocados aqui
    public Projeto(String nome, String professor, double despesasCapitalPrevisto, double materialConsumoPrevisto, double servicosPessoaFisicaPrevisto, double servicosPessoaJuridicaPrevisto, double diariasPrevisto, double passagensPrevisto) {
        this.nome = nome;
        this.professor = professor;
        this.despesasCapitalPrevisto = despesasCapitalPrevisto;
        this.materialConsumoPrevisto = materialConsumoPrevisto;
        this.servicosPessoaFisicaPrevisto = servicosPessoaFisicaPrevisto;
        this.servicosPessoaJuridicaPrevisto = servicosPessoaJuridicaPrevisto;
        this.diariasPrevisto = diariasPrevisto;
        this.passagensPrevisto = passagensPrevisto;
    }

    public int getId(){return this.id;}
    public void setId(int id){this.id=id;}
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return this.professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public double getDespesasCapitalPrevisto() {
        return this.despesasCapitalPrevisto;
    }

    public void setDespesasCapitalPrevisto(double despesasCapitalPrevisto) {
        this.despesasCapitalPrevisto = despesasCapitalPrevisto;
    }

    public double getDespesasCapitalGasto() {
        return this.despesasCapitalGasto;
    }


    public void setDespesasCapitalGasto(double despesasCapitalGasto) {
        this.despesasCapitalGasto = despesasCapitalGasto;
    }

    public double getMaterialConsumoPrevisto() {
        return this.materialConsumoPrevisto;
    }

    public void setMaterialConsumoPrevisto(double materialConsumoPrevisto) {
        this.materialConsumoPrevisto = materialConsumoPrevisto;
    }

    public double getMaterialConsumoGasto() {
        return this.materialConsumoGasto;
    }

    public void setMaterialConsumoGasto(double materialConsumoGasto) {
        this.materialConsumoGasto = materialConsumoGasto;
    }

    public double getServicosPessoaFisicaPrevisto() {
        return this.servicosPessoaFisicaPrevisto;
    }

    public void setServicosPessoaFisicaPrevisto(double servicosPessoaFisicaPrevisto) {
        this.servicosPessoaFisicaPrevisto = servicosPessoaFisicaPrevisto;
    }

    public double getServicosPessoaFisicaGasto() {
        return this.servicosPessoaFisicaGasto;
    }

    public void setServicosPessoaFisicaGasto(double servicosPessoaFisicaGasto) {
        this.servicosPessoaFisicaGasto = servicosPessoaFisicaGasto;
    }

    public double getServicosPessoaJuridicaPrevisto() {
        return this.servicosPessoaJuridicaPrevisto;
    }

    public void setServicosPessoaJuridicaPrevisto(double servicosPessoaJuridicaPrevisto) {
        this.servicosPessoaJuridicaPrevisto = servicosPessoaJuridicaPrevisto;
    }

    public double getServicosPessoaJuridicaGasto() {
        return this.servicosPessoaJuridicaGasto;
    }

    public void setServicosPessoaJuridicaGasto(double servicosPessoaJuridicaGasto) {
        this.servicosPessoaJuridicaGasto = servicosPessoaJuridicaGasto;
    }

    public double getDiariasPrevisto() {
        return this.diariasPrevisto;
    }

    public void setDiariasPrevisto(double diariasPrevisto) {
        this.diariasPrevisto = diariasPrevisto;
    }

    public double getDiariasGasto() {
        return this.diariasGasto;
    }

    public void setDiariasGasto(double diariasGasto) {
        this.diariasGasto = diariasGasto;
    }

    public double getPassagensPrevisto() {
        return this.passagensPrevisto;
    }

    public void setPassagensPrevisto(double passagensPrevisto) {
        this.passagensPrevisto = passagensPrevisto;
    }

    public double getPassagensGasto() {
        return this.passagensGasto;
    }

    public void setPassagensGasto(double passagensGasto) {
        this.passagensGasto = passagensGasto;
    }

    //capitais disponiveis
    public double getDespesasCapitalDisponivel(){return this.getDespesasCapitalPrevisto()-this.getDespesasCapitalGasto();}
    public double getMaterialConsumoDisponivel(){return this.getMaterialConsumoPrevisto()-this.getMaterialConsumoGasto();}
    public double getServicosPessoaFisicaDisponivel(){return this.getServicosPessoaFisicaPrevisto()-this.getServicosPessoaFisicaGasto();}
    public double getServicosPessoaJuridicaDisponivel(){return this.getServicosPessoaJuridicaPrevisto()-this.getServicosPessoaJuridicaGasto();}
    public double getDiariasDisponivel(){return this.getDiariasPrevisto()-this.getDiariasGasto();}
    public double getPassagensDisponivel(){return this.getPassagensPrevisto()-this.getPassagensGasto();}

    
    //metodo descreve projeto
    //o id nao entra porque eu nao tenho o que fazer com ele
    public String getDescricao(){
        String descricao;
        descricao = "("
        +this.getNome()
        +", "+this.getProfessor()
        +", "+this.getDespesasCapitalPrevisto()
        +", "+this.getDespesasCapitalGasto()
        +", "+this.getMaterialConsumoPrevisto()
        +", "+this.getMaterialConsumoGasto()
        +", "+this.getServicosPessoaFisicaPrevisto()
        +", "+this.getServicosPessoaFisicaGasto()
        +", "+this.getServicosPessoaJuridicaPrevisto()
        +", "+this.getServicosPessoaJuridicaGasto()
        +", "+this.getDiariasPrevisto()
        +", "+this.getDiariasGasto()
        +", "+this.getPassagensPrevisto()
        +", "+this.getPassagensGasto()
        +")";
        return descricao;
    }

    @Override
    public String toString() {
        String endl="\n";
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" + "\n"+
            ", professor='" + getProfessor() + "'" + endl+
            ", despesasCapitalPrevisto='" + getDespesasCapitalPrevisto() + "'" +endl+
            ", despesasCapitalGasto='" + getDespesasCapitalGasto() + "'" +endl+
            ", materialConsumoPrevisto='" + getMaterialConsumoPrevisto() + "'" +endl+
            ", materialConsumoGasto='" + getMaterialConsumoGasto() + "'" +endl+
            ", servicosPessoaFisicaPrevisto='" + getServicosPessoaFisicaPrevisto() + "'" +endl+
            ", servicosPessoaFisicaGasto='" + getServicosPessoaFisicaGasto() + "'" +endl+
            ", servicosPessoaJuridicaPrevisto='" + getServicosPessoaJuridicaPrevisto() + "'" +endl+
            ", servicosPessoaJuridicaGasto='" + getServicosPessoaJuridicaGasto() + "'" +endl+
            ", diariasPrevisto='" + getDiariasPrevisto() + "'" +endl+
            ", diariasGasto='" + getDiariasGasto() + "'" +endl+
            ", passagensPrevisto='" + getPassagensPrevisto() + "'" +endl+
            ", passagensGasto='" + getPassagensGasto() + "'" +endl+
            "}";
    }


}
