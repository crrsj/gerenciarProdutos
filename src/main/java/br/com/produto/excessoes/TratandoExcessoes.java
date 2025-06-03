package br.com.produto.excessoes;

import br.com.produto.dto.MensagemDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratandoExcessoes {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemDeErro>erroAoCadastrar(){
        var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST,"Estoque abaixo de 5,não é possível cadastrar o produto!");
        return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
    }
}
