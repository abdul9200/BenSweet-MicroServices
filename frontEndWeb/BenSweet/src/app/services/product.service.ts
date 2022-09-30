import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url = "http://localhost:8084/products/"

  constructor(private httpClient:HttpClient) { }
  getProducts(){
    return this.httpClient.get(this.url);
  }
  getProductsByCategory(id : number){
    return this.httpClient.get(this.url+"category/"+id);
  }
  getProductsByPatisserie(id : number){
    return this.httpClient.get(this.url+"patisserie/"+id);
  }
  deleteProduct(id:number){
    return this.httpClient.delete(this.url+id);
  }
  getProduct(id:number){
    return this.httpClient.get(this.url+id);
  }
  getPatisseries(){

    return this.httpClient.get("http://localhost:8084/patisseries/");
  }
  getCategories(){

    return this.httpClient.get("http://localhost:8084/categories/");
  }

}
