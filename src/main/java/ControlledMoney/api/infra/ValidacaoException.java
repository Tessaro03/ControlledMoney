package ControlledMoney.api.infra;

public class ValidacaoException extends RuntimeException{
    
    public ValidacaoException(String mensagem){
        super(mensagem);
    }

}
