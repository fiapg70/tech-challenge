package br.com.postech.sevenfood.core.utils;

public enum StatusEnum {

    NOVO("Novo", 1L),
    EM_PROCESSAMENTO("Em Processamento", 2L),
    ENVIADO("Enviado", 3L),

    ENTREGUE("Entregue", 4L),

    FINALIZADO("Finalizado", 5L),
    CANCELADO("Cancelado", 6L);

    private final String status;
    private final Long cod;

    StatusEnum(String status, Long cod) {
        this.status = status;
        this.cod = cod;
    }

    public String getStatus() {
        return status;
    }

    public Long getCod() {
        return cod;
    }
    public static StatusEnum getByCod(Long cod) {
        for (StatusEnum statusEnum : values()) {
            if (statusEnum.getCod().equals(cod)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + cod);
    }


}

