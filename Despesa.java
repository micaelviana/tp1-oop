public class Despesa {
    private int id;
    private Projeto projeto;
    private String descricao;
    private int categoria;
    private double valor;

    public Despesa(){}

    public Despesa(Projeto projeto, String descricao, int categoria, double valor) {
        this.projeto = projeto;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Projeto getProjeto() {
        return this.projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoria() {
        return this.categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", projeto='" + getProjeto() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }

}