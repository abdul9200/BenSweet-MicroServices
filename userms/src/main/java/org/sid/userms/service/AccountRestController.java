package org.sid.userms.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.sid.userms.entities.AppRole;
import org.sid.userms.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class AccountRestController {

    public AccountService accountService;
    @GetMapping(path = "/users")
    public List<AppUser> appUsers(){
        return accountService.listUsers();
    }
    @PostMapping(path = "/users")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addUser(appUser);
    }
    @PostMapping(path="/roles")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    //@PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRolename());

    }
    @DeleteMapping(path="/deleteRoleToUser")
    public void deleteRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.deleteRoleToUser(roleUserForm.getUsername(),roleUserForm.getRolename());

    }
    @DeleteMapping(path="/deleteUser")
    public void removeUser(@RequestBody AppUser appUser){
        accountService.deleteUser(appUser.getUsername());
    }

}

@Data
class RoleUserForm{
    private String username;
    private String rolename;
}
