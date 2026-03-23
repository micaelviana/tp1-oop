public class ValorInconversivelException extends InsercaoException{
    private static final long serialVersionUID = 5L;
    
    public ValorInconversivelException() {
        this("O valor inserido nao é um número inteiro ou flutuante");
    }

    public ValorInconversivelException(String s) {
        super(s);
    }

}
