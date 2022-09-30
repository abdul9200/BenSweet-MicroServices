import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LivraisonService {
  private url = "http://localhost:8085/livraisons"

  constructor(private httpClient:HttpClient) { }
  getLivraisons(){
    return this.httpClient.get(this.url);
  }
  deleteLivraison(id: number){
    console.log(this.url+'/'+id);

    return this.httpClient.delete(this.url+"/"+id);
  }
  getLivraison(id:number){
    return this.httpClient.get(this.url+"/"+id);
  }
  getOrders(){
    return this.httpClient.get("http://localhost:8085/orderslist")
  }
  getClient(id:number){
    return this.httpClient.get("http://localhost:8083/users/"+id);
  }
}
