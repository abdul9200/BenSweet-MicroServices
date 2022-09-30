import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
private url="http://localhost:8083/roles/"
  constructor(private httpClient:HttpClient) { }
  getRoles(){
    return this.httpClient.get(this.url)

  }
  deleteRole(id:number){
    return this.httpClient.delete(this.url+id)
  }
}
