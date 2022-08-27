package org.sid.userms.service;

import lombok.AllArgsConstructor;
import org.sid.userms.entities.AppRole;
import org.sid.userms.entities.AppUser;
import org.sid.userms.repositories.RoleRepository;
import org.sid.userms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository appUserRepository;
    @Autowired
    private RoleRepository appRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public AppUser addUser(AppUser appUser) {

        String p = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(p));
        AppRole appRole =appRoleRepository.findByRoleName("USER");
        appUser.getAppRoles().add(appRole);
        return appUserRepository.save(appUser);

    }


    public AppRole addRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }


    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);

    }


    public AppUser loadUserByUsername(String userName) {

        return appUserRepository.findByUsername(userName);
    }


    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }


    public void deleteUser(String userName) {
        AppUser appUser=loadUserByUsername(userName);
        appUserRepository.delete(appUser);

    }

    @Override
    public void deleteRoleToUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().removeIf(role->role==appRole);
        appUserRepository.save(appUser);

    }
}
