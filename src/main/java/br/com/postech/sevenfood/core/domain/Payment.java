package br.com.postech.sevenfood.core.domain;

public record Payment (Long id, Long clientId, Long orderId, Long status){

}
