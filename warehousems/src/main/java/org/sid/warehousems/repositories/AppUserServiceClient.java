package org.sid.warehousems.repositories;

import org.sid.warehousems.entities.AppUser;
import org.sid.warehousems.entities.RoleUserForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="user-service")
public interface AppUserServiceClient {
    @GetMapping("/appUsers/{id}?projection=p1")
    AppUser findAppUserById(@PathVariable("id") Long id);
    @PostMapping(path = "/addRoleToUser")
    void addRoleToUser(@RequestBody RoleUserForm roleUserForm);
    @DeleteMapping(path = "/deleteRoleToUser")
    void deleteRoleToUser(@RequestBody RoleUserForm roleUserForm);


}
