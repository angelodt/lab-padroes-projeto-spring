package br.com.angelodt.dio.lab_padroes_projeto_spring.handlerException;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem) {
        super(mensagem);
    }

    public BusinessException(String mensagem, Object ... params) {
        super(String.format(mensagem, params));
    }
}
