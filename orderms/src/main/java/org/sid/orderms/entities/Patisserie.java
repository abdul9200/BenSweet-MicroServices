package org.sid.orderms.entities;

import lombok.Data;



@Data
public class Patisserie {
    private Long id;
    private String name;
    private String description;
    private String photo;
    private double solde=0;


}
