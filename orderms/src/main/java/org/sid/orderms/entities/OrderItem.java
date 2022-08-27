package org.sid.orderms.entities;

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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Product product;
    private long productID;
    /*
    @ManyToOne
    private Product product;*/
    private int quantity;
    private double price;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;
}
