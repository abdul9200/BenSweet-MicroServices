import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient:HttpClient) { }
  getOrders(){
    return this.httpClient.get("http://localhost:8085/orderslist")
  }
  deleteOrder(id:number){
    return this.httpClient.delete("http://localhost:8085/orders/"+id)
  }
  getProducts(){
    return this.httpClient.get("http://localhost:8084/products/")
  }
  getUsers(){
    return this.httpClient.get("http://localhost:8083/users/")
  }
}
