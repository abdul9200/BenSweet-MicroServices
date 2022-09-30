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
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivraison;
    private String name;
    private String adress;

    private Enum<LivraisonEtat> livraisonEtatEnum;
    @OneToOne(mappedBy = "livraison")

    private Order order;
}