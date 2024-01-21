package br.com.postech.sevenfood.core.utils;

public enum PaymentEnum {
    EM_PROCESSAMENTO(1L, "Em Processamento"),
    APROVADO(2L, "Aprovado"),
    RECUSADO(3L, "Recusado");


    private final Long cod;
    private final String status;

    PaymentEnum(Long cod, String status) {
        this.cod = cod;
        this.status = status;
    }

    public Long getCod() {
        return cod;
    }

    public String getStatus() {
        return status;
    }

    public static PaymentEnum getByCod(Long cod) {
        for (PaymentEnum status : values()) {
            if (status.getCod().equals(cod)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de pagamento inválido: " + cod);
    }


}
