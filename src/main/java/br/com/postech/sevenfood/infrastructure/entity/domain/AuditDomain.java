package br.com.postech.sevenfood.infrastructure.entity.domain;

import br.com.postech.sevenfood.commons.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public class AuditDomain implements Serializable {

    private static final String CURRENT_USER = "root@localhost";

    @NotNull
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdDate = DateUtil.convert(new Date());

    @NotNull
    @Column(name = "create_by")
    @JsonIgnore
    @CreatedBy
    private String createBy = CURRENT_USER;

    @Column(name = "last_modified_date")
    @JsonIgnore
    @LastModifiedDate
    @Getter
    @Setter
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_modified_by")
    @JsonIgnore
    @LastModifiedBy
    @Getter
    @Setter
    private String lastModifiedBy;

    @Column(name = "status", nullable = false)
    @NotNull(message = "o campo \"status\" é obrigario")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;
}
