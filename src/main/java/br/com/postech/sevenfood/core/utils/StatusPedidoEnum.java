package br.com.postech.sevenfood.core.utils;

public enum StatusPedidoEnum {

    RECEBIDO("Recebido", 1L),
    EM_PREPARACAO("Em Preparacao", 2L),
    PRONTO("Pronto", 3L);

    private final String status;
    private final Long cod;

    StatusPedidoEnum(String status, Long cod) {
        this.status = status;
        this.cod = cod;
    }

    public String getStatus() {
        return status;
    }

    public Long getCod() {
        return cod;
    }
    public static StatusPedidoEnum getByCod(Long cod) {
        for (StatusPedidoEnum statusPedidoEnum : values()) {
            if (statusPedidoEnum.getCod().equals(cod)) {
                return statusPedidoEnum;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + cod);
    }


}

