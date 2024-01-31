package ControlledMoney.api.infra.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoDeErro {
    
        @ExceptionHandler(ValidacaoException.class)
        public ResponseEntity mensagemValidacao(ValidacaoException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarErro404() {
            return ResponseEntity.notFound().build();
        }
        
        
        private record DadosErroValidacao(String campo, String mensagem) {
            public DadosErroValidacao(FieldError erro) {
                this(erro.getField(), erro.getDefaultMessage());
            }
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
            var erros = ex.getFieldErrors();
            return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());

        }


}