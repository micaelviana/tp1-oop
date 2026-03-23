public class ValorNegativoException extends InsercaoException{
    private static final long serialVersionUID = 6L;
    
    public ValorNegativoException() {
        this("Os valores nao podem ser negativos");
    }

    public ValorNegativoException(String s) {
        super(s);
    }

}
