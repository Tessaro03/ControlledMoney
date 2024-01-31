package ControlledMoney.api.infra.Exceptions;

public class ValidacaoException extends RuntimeException{
    
    public ValidacaoException(String mensagem){
        super(mensagem);
    }

}
