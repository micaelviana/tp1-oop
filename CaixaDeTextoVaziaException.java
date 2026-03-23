public class CaixaDeTextoVaziaException extends InsercaoException{
    private static final long serialVersionUID = 2L;
    
    public CaixaDeTextoVaziaException() {
        this("Caixa de entrada de dados não foi preenchida");
    }

    public CaixaDeTextoVaziaException(String s) {
        super(s);
    }

}
