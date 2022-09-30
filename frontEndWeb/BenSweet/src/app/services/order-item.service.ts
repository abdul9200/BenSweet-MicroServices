import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {

  constructor(private httpClient:HttpClient) { }
  getOrderItems(){
    return this.httpClient.get("http://localhost:8085/orderItems");
  }
  getOrderItem(id : number){
    return this.httpClient.get("http://localhost:8085/orderItems/"+id)
  }
  deleteOrderItem(id:number){
    return this.httpClient.delete("http://localhost:8085/orderItems/"+id)
  }
  getProducts(){
    return this.httpClient.get("http://localhost:8084/products/")
  }
  getOrders(){
    return this.httpClient.get("http://localhost:8085/orderslist")
  }
}
