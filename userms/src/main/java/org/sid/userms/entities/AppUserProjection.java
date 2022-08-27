package org.sid.userms.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1" , types = AppUser.class)
public interface AppUserProjection {
    public Long getId();
    public String getUsername();
    public String getEmail();
    public String getTelephone();

}
