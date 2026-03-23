import javax.swing.JButton;

public class BotaoComId {
    private int id;
    private JButton botao;
    private char tipo;

    public BotaoComId(int id, String titulo, char tipo) {
        this.id = id;
        this.tipo = tipo;

        this.botao = new JButton(titulo);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JButton getBotao() {
        return this.botao;
    }

    public void setBotao(JButton botao) {
        this.botao = botao;
    }

    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

}
