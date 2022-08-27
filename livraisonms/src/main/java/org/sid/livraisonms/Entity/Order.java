package org.sid.livraisonms.Entity;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private AppUser appUser;
    public double TotalAmount;
}
