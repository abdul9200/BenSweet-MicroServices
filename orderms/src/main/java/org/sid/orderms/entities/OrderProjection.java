package org.sid.orderms.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "p1",types=Order.class)
public interface OrderProjection {
    public Long getId();
    public AppUser getClient();
    public double getTotalAmount();

}
