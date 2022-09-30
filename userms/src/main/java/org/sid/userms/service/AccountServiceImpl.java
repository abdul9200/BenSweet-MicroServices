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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<AppRole> appRoles1=new HashSet<>();
        appRoles1.add(appRole);
        appUser.setAppRoles(appRoles1);
        return appUserRepository.save(appUser);

    }


    public AppRole addRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppUser updateUser(AppUser appUser, Long id) {
        appUser.setAppRoles(appUserRepository.findById(id).get().getAppRoles());
        appUser.setId(id);
        return appUserRepository.save(appUser);
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
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }


    @Override
    public void deleteRoleToUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().removeIf(role->role==appRole);
        appUserRepository.save(appUser);

    }

    @Override
    public AppUser getUser(Long id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public List<AppRole> getRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppRole updateRole(AppRole appRole, Long id) {
        appRole.setId(id);
        return appRoleRepository.save(appRole);
    }


    @Override
    public void deleteRole(Long id) {
        appRoleRepository.deleteById(id);

    }


}
