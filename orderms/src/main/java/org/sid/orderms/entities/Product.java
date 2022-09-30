package org.sid.orderms.entities;

import lombok.Data;


@Data
public class Product {
    private Long id;
    private String designation;
    private double currentPrice;

    private Patisserie patisserie;
}
