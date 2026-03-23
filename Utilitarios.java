public class Utilitarios {
    public boolean stringVazia(String str){
        if(str==null || str.length()==0){
            return true;
        }
        
        //se achar qualquer coisa que nao seja um espaco significa que nao eh uma string que so contem espacos
        //entao o nome eh valido
        for(char it: str.toCharArray()){
            if(it != ' '){
                return false;
            }
        }

        //passou do array e tudo era espaco
        return true;
    }

    public String capturaTexto(String str) throws CaixaDeTextoVaziaException{
        if(stringVazia(str)==true) throw new CaixaDeTextoVaziaException();
        return str;
    }

    public Double converteTextoParaDouble(String str) throws CaixaDeTextoVaziaException, ValorInconversivelException,ValorNegativoException{
        //1. Nao foi inserido nada
        if (stringVazia(str) == true){
            throw new CaixaDeTextoVaziaException();
        }
        //2. Nao foi inserido um um valor conversivel
        try {
            Double.parseDouble(str);
        } catch (Exception exception) {
            throw new ValorInconversivelException();
        }

        //3. O valor eh negativo
        if(Double.parseDouble(str)<0){
            throw new ValorNegativoException();
        }

        //Passou por todas as provas de controle de qualidade
        return Double.parseDouble(str);
    }


    public int converteTextoParaInteger(String str) throws CaixaDeTextoVaziaException, ValorInconversivelException,ValorNegativoException{
        //1. Nao foi inserido nada
        if (stringVazia(str) == true){
            throw new CaixaDeTextoVaziaException();
        }
        //2. Nao foi inserido um um valor conversivel
        try {
            Integer.parseInt(str);
        } catch (Exception exception) {
            throw new ValorInconversivelException();
        }

        //3. O valor eh negativo
        if(Integer.parseInt(str)<0){
            throw new ValorNegativoException();
        }

        //Passou por todas as provas de controle de qualidade
        return Integer.parseInt(str);
    }
}
