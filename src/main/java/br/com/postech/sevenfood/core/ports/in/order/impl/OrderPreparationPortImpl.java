package br.com.postech.sevenfood.core.ports.in.order.impl;

import br.com.postech.sevenfood.core.domain.Order;
import br.com.postech.sevenfood.core.domain.OrderPreparation;
import br.com.postech.sevenfood.core.domain.Product;
import br.com.postech.sevenfood.core.domain.ProductCategory;
import br.com.postech.sevenfood.core.ports.in.order.OrderPreparationPort;
import br.com.postech.sevenfood.core.service.OrderPreparationService;
import br.com.postech.sevenfood.infrastructure.entity.queue.QueueOrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderPreparationPortImpl implements OrderPreparationPort {

    private final OrderPreparationService orderPreparationService;
    @Override
    public void queueOrder(Order order) throws JsonProcessingException {

        List<Product> products = new ArrayList<>();

        order.getProducts().forEach(product -> {
            Product productResume = new Product();
            productResume.setName(product.getName());
            productResume.setDescription(product.getDescription());

            ProductCategory category = new ProductCategory();
            category.setName(product.getProductCategory().getName());
            productResume.setProductCategory(category);

            products.add(productResume);
        });

        OrderPreparation build = OrderPreparation.builder().name(order.getClient().getName())
                .id(order.getId())
                .products(products).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        QueueOrderEntity entity = new QueueOrderEntity();
        String json = objectMapper.writeValueAsString(build);
        entity.setJson(json);

        orderPreparationService.sendRequestQueue(entity);

    }
}
