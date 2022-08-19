package com.eatco.compensationservice.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.eatco.compensationservice.config.jpa.CompensationIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compensation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compensation  extends AuditEntity{
    @Id
    @Column(name = "cmp_id", unique = true, nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compensation_seq")
    @GenericGenerator(
            name = "compensation_seq",
            strategy = "com.eatco.compensationservice.config.jpa.CompensationIdGenerator",
            parameters = {
                    @Parameter(name = CompensationIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = CompensationIdGenerator.VALUE_PREFIX_PARAMETER, value = "CMP_"),
                    @Parameter(name = CompensationIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
    private String cmpId;
    @Column(name = "currency")
    private String currency;
    @OneToMany(mappedBy = "compensation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompensationDetail> compensationDetails;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "co2_total_amount")
    private BigDecimal co2TotalAmount;
}
