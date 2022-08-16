package com.eatco.compensationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "compensation_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompensationDetail  extends AuditEntity {

    @EmbeddedId
    private Cmp_detail_id cmp_detail_id;
    @Column(name = "quantity")
    private int quantity;
    @MapsId("cmpId")
    @ManyToOne
    @JoinColumn(name = "cmpId")
    private Compensation compensation;
    @Column(name = "amount" , nullable = false)
    private BigDecimal amount;
    @Column(name = "co2_amount" , nullable = false)
    private BigDecimal co2Amount;
}
