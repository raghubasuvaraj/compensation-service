package com.eatco.compensationservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AuditEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "name")
    private String name;
    /*@JsonManagedReference
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Item> itemList;*/
}
