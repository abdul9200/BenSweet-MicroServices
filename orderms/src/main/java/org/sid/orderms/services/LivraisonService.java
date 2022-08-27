package org.sid.orderms.services;

import org.sid.orderms.entities.Livraison;

public interface LivraisonService {
    public Livraison addLivraison(Livraison livraison , Long idOrder);
    public Livraison updateLivraison(Long idLivraison,Livraison livraison);
    public Livraison updateLivraison(Long idLivraison,Livraison livraison,Long idOrder);
    public void removeLivraison(Long idLivraison);
    public Livraison addLivraisonToOrder(Long idLivraison,Long idOrder);
}
