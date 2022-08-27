package org.sid.warehousems.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patisserie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String photo;
    private double solde=0;
    @OneToMany( mappedBy = "patisserie")
    private Collection<Product> products;
    @Transient
    private AppUser moderateur;
    private double moderateurID;

}
