public class InsercaoException extends SistemaException{
    private static final long serialVersionUID = 3L;
    
    public InsercaoException() {
        this("Problema ao inserir dados no sistema");
    }

    public InsercaoException(String s) {
        super(s);
    }

}
