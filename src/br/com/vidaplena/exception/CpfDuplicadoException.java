package br.com.vidaplena.exception;

public class CpfDuplicadoException extends Exception {
    public CpfDuplicadoException(String mensagem) {
        super(mensagem);
    }
}