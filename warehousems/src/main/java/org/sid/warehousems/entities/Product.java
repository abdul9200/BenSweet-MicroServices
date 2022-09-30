package org.sid.warehousems.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private String description;
    private double currentPrice;
    private boolean promotion;
    private boolean selected=false;
    private boolean available=true;
    private String photoName;

    private int quantityStock=1;

    private int quantitySold=0;
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private  Category category;
    @JsonIgnore
    @ManyToOne
    private Patisserie patisserie;
}
