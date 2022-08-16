package com.eatco.compensationservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends AuditEntity{

    @Id
    @Column(name = "item_nbr")
    @GeneratedValue(generator = "item_seq")
    @GenericGenerator(
            name = "item_seq",
            strategy = "com.eatco2.compensationservice.config.jpa.ItemNbrGenerator",
            parameters = {
                    @Parameter(name = ItemNbrGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = ItemNbrGenerator.VALUE_PREFIX_PARAMETER, value = "ITEM_"),
                    @Parameter(name = ItemNbrGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String itemNbr;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "co2_amount")
    private BigDecimal co2Amount;
    /*@ManyToOne
    @JsonBackReference
    @JoinColumn(name="category_id", nullable = false)
    private Category category;*/
}
