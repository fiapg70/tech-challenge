package br.com.postech.sevenfood.core.ports.in.client;

import br.com.postech.sevenfood.core.domain.Client;

public interface FindByIdClientPort {
    Client findById(Long id);
}
