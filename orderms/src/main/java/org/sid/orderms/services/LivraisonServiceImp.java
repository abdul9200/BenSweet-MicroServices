package org.sid.orderms.services;

import org.sid.orderms.entities.Livraison;
import org.sid.orderms.entities.LivraisonEtat;
import org.sid.orderms.entities.Order;
import org.sid.orderms.repositories.LivraisonRepository;
import org.sid.orderms.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LivraisonServiceImp implements LivraisonService{
    @Autowired
    LivraisonRepository livraisonRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Livraison getLivraison(Long idLivraison) {
        return livraisonRepository.findById(idLivraison).get();
    }

    @Override
    public List<Livraison> getLivraisons() {
        return livraisonRepository.findAll();
    }

    @Override
    public Livraison addLivraison(Livraison livraison, Long idOrder) {
        livraison.setOrder(orderRepository.findById(idOrder).get());
        livraison.setLivraisonEtatEnum(LivraisonEtat.PreparationDeCommande);
        Order order=orderRepository.findById(idOrder).get();
        order.setLivraison(livraison);
        return livraisonRepository.save(livraison);
    }

    @Override
    public Livraison updateLivraison(Long idLivraison, Livraison livraison) {
        livraison.setOrder(livraisonRepository.findById(idLivraison).get().getOrder());
        livraison.setIdLivraison(idLivraison);

        return livraisonRepository.save(livraison);
    }

    @Override
    public Livraison updateLivraison(Long idLivraison, Livraison livraison, Long idOrder) {
        Order order =orderRepository.findById(idOrder).get();
        livraison.setIdLivraison(idLivraison);
        livraison.setOrder(order);
        order.setLivraison(livraison);
        return livraisonRepository.save(livraison);
    }


    @Override
    public void removeLivraison(Long idLivraison) {
        Livraison livraison=livraisonRepository.findById(idLivraison).get();
        if(livraison.getLivraisonEtatEnum()== LivraisonEtat.PreparationDeCommande) {
            orderRepository.findById(livraison.getOrder().getId()).get().setLivraison(null);
            livraisonRepository.deleteById(idLivraison);
        }

    }

    @Override
    public Livraison addLivraisonToOrder(Long idLivraison, Long idOrder) {
        Livraison livraison= livraisonRepository.findById(idLivraison).get();
        Order order=orderRepository.findById(idOrder).get();
        livraison.setOrder(order);
        order.setLivraison(livraison);
        return livraisonRepository.save(livraison);
    }
}
