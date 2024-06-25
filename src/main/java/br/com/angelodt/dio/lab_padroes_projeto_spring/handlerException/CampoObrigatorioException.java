package br.com.angelodt.dio.lab_padroes_projeto_spring.handlerException;

public class CampoObrigatorioException extends BusinessException {

    public CampoObrigatorioException(String campo) {
        super("É obrigatório o preenchimento do campo %s!", campo);
    }

    public CampoObrigatorioException(String customMessage, String campo) {
        super(customMessage, campo);
    }

    public CampoObrigatorioException(String campo, int tamanhoMinimo) {
        super("É obrigatório o preenchimento do campo %s com no minimo %d caracteres!", campo, tamanhoMinimo);
    }
}
