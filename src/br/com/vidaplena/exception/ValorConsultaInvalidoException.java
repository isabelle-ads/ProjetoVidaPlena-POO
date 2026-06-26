package br.com.vidaplena.exception;

public class ValorConsultaInvalidoException extends RuntimeException {
    public ValorConsultaInvalidoException(String mensagem) {
        super(mensagem);
    }
}