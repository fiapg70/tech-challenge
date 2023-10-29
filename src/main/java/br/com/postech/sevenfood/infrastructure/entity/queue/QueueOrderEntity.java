package br.com.postech.sevenfood.infrastructure.entity.queue;

import br.com.postech.sevenfood.infrastructure.entity.client.ClientEntity;
import br.com.postech.sevenfood.infrastructure.entity.domain.AuditDomain;
import br.com.postech.sevenfood.infrastructure.entity.product.ProductEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_queue_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Restaurant object")
public class QueueOrderEntity {


    @Schema(description = "Unique identifier of the Product.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String json;

}
