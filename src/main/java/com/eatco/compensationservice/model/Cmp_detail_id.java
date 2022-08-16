package com.eatco.compensationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cmp_detail_id implements Serializable {
    @Column(name = "item_nbr")
    private String itemNbr;
    @Column(name = "category_id")
    private Long categoryId;
    private String cmpId;

}
