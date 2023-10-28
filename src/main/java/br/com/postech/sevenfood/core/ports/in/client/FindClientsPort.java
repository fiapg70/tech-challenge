package br.com.postech.sevenfood.core.ports.in.client;

import br.com.postech.sevenfood.core.domain.Client;

import java.util.List;

public interface FindClientsPort {
    List<Client> findAll();
}
