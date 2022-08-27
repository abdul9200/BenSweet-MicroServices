package org.sid.userms.service;

import org.sid.userms.entities.AppRole;
import org.sid.userms.entities.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
public interface AccountService {
    AppUser addUser(AppUser appUser);
    AppRole addRole(AppRole appRole);
    void addRoleToUser(String userName,String roleName);
    AppUser loadUserByUsername(String userName);
    List<AppUser> listUsers();
    void deleteUser(String userName);
    void deleteRoleToUser(String userName,String roleName);
}
