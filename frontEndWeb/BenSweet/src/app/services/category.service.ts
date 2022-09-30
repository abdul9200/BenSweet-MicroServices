import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private url = "http://localhost:8084/categories/"

  constructor(private httpClient : HttpClient) { }
  getCategories(){

    return this.httpClient.get(this.url);
  }
  deleteCategory(id:number){

    return this.httpClient.delete(this.url+id);
  }
  getCategory(id : number){
    return this.httpClient.get(this.url+id)
  }
}
