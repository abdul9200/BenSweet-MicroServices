package org.sid.warehousems.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class RoleUserForm {
    private String username;
    private String rolename="Modérateur";
}
