package org.sid.orderms.services;

import org.sid.orderms.entities.Livraison;

import java.util.List;

public interface LivraisonService {
    public Livraison getLivraison(Long idLivraison);
    public List<Livraison> getLivraisons();
    public Livraison addLivraison(Livraison livraison , Long idOrder);
    public Livraison updateLivraison(Long idLivraison,Livraison livraison);
    public Livraison updateLivraison(Long idLivraison,Livraison livraison,Long idOrder);
    public void removeLivraison(Long idLivraison);
    public Livraison addLivraisonToOrder(Long idLivraison,Long idOrder);
}
