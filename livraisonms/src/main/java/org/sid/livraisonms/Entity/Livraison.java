package org.sid.livraisonms.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivraison;
    private String name;
    private String adress;
    private Enum<LivraisonEtat> livraisonEtatEnum;
    @Transient
    private Order order;
    private long orderID;
    /*
    @OneToOne(mappedBy = "livraison")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;
     */
}
