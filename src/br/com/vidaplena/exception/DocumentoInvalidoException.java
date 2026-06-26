package br.com.vidaplena.exception;

public class DocumentoInvalidoException extends RuntimeException {
    public DocumentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}