package org.sid.orderms.repositories;

import org.sid.orderms.entities.AppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@CrossOrigin(origins = "*")
@FeignClient(name="user-service")
public interface AppUserServiceClient {
    @GetMapping("/appUsers/{id}?projection=p1")
    AppUser findAppUserById(@PathVariable("id") Long id);
}
