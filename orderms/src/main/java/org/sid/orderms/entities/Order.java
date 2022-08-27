package org.sid.orderms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private Collection<OrderItem> orderItems;
    @Transient
    private AppUser client;
    private long clientID;
    private double totalAmount=0;
    @OneToOne
    private Livraison livraison;
}
