public class SistemaException extends Exception {
    private static final long serialVersionUID = 4L;
    
    public SistemaException() {
        this("Excecao geral do sistema");
    }

    public SistemaException(String s) {
        super(s);
    }

}
