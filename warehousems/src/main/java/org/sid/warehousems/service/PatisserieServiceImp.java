package org.sid.warehousems.service;

import com.netflix.discovery.converters.Auto;
import org.sid.warehousems.entities.AppUser;
import org.sid.warehousems.entities.Patisserie;
import org.sid.warehousems.entities.RoleUserForm;
import org.sid.warehousems.repositories.AppUserServiceClient;
import org.sid.warehousems.repositories.PatisserieRepository;
import org.sid.warehousems.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatisserieServiceImp implements PatisserieService {
    @Autowired
    PatisserieRepository patisserieRepository;
    @Autowired
    AppUserServiceClient appUserServiceClient;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Patisserie addPatisserie(Patisserie patisserie) {
        AppUser appUser=appUserServiceClient.findAppUserById((long) patisserie.getModerateurID());
        RoleUserForm roleUserForm= new RoleUserForm(appUser.getUsername(),"Moderateur");
        appUserServiceClient.addRoleToUser(roleUserForm);
        return patisserieRepository.save(patisserie);
    }




    @Override
    public Patisserie updatePatisserie(Patisserie patisserie,Long idPatisserie) {
        Patisserie patisserie1=patisserieRepository.findById(idPatisserie).get();
        AppUser appUserAnterior=appUserServiceClient.findAppUserById((long) patisserie1.getModerateurID());
        RoleUserForm roleUserFormAnterior= new RoleUserForm(appUserAnterior.getUsername(),"Moderateur");
        System.out.println("calculating...");
        long numPatisserie=patisserieRepository.countByModerateurID(patisserie1.getModerateurID());
        System.out.println("Num de patisserie "+numPatisserie+" chez "+appUserAnterior.getUsername());
        if(numPatisserie==1) {
            System.out.println("t7an a m3alem");
            appUserServiceClient.deleteRoleToUser(roleUserFormAnterior);
        }
        patisserie.setId(idPatisserie);
        AppUser appUser=appUserServiceClient.findAppUserById((long) patisserie.getModerateurID());
        RoleUserForm roleUserForm= new RoleUserForm(appUser.getUsername(),"Moderateur");
        appUserServiceClient.addRoleToUser(roleUserForm);
        return patisserieRepository.save(patisserie);
    }

    @Override
    public Patisserie getPatisserie(Long id) {
        Patisserie patisserie=patisserieRepository.findById(id).get();
        patisserie.setModerateur(appUserServiceClient.findAppUserById((long) patisserie.getModerateurID()));
        patisserie.setProducts(productRepository.findByPatisserieId(id));
        return patisserie;
    }

    @Override
    public List<Patisserie> getPatisseries() {
        List<Patisserie> patisseries=patisserieRepository.findAll().stream().toList();
        patisseries.forEach(patisserie -> {
            patisserie.setModerateur(appUserServiceClient.findAppUserById((long) patisserie.getModerateurID()));
            patisserie.setProducts(productRepository.findByPatisserieId(patisserie.getId()));

        });
        return patisseries;
    }

    @Override
    public void deletePatisserie(Long id) {
        patisserieRepository.deleteById(id);
    }

    @Override
    public Patisserie addSold(Long idPatisserie, double solde) {
        Patisserie patisserie=patisserieRepository.findById(idPatisserie).get();
        patisserie.setSolde(patisserie.getSolde()+solde);
        return patisserieRepository.save((patisserie));
    }

    @Override
    public Patisserie removeSold(Long idPatisserie, double solde) {
        Patisserie patisserie=patisserieRepository.findById(idPatisserie).get();
        patisserie.setSolde(patisserie.getSolde()+solde);
        return patisserieRepository.save((patisserie));
    }
}
