package br.com.postech.sevenfood.core.service;

import br.com.postech.sevenfood.infrastructure.entity.queue.QueueOrderEntity;
import br.com.postech.sevenfood.infrastructure.repository.QueueOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderPreparationService {

    private final QueueOrderRepository repository;

    public void sendRequestQueue(QueueOrderEntity entity) {

        repository.save(entity);

    }

}
