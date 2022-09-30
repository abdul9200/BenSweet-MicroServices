import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private url = "http://localhost:8083/users/"

  constructor(private httpClient:HttpClient) { }
  getUsers(){
    return this.httpClient.get(this.url);
  }
  deleteUser(id:number){
    return this.httpClient.delete(this.url+id)
  }
  getRoleofUser(id:number){

    return this.httpClient.get(this.url+id)
  }
  getRoles(){
    return this.httpClient.get("http://localhost:8888/USER-SERVICE/roles")
  }
  RetrieveRoleFromUser(userRole:any){

    return this.httpClient.delete("http://localhost:8888/USER-SERVICE/deleteRoleToUser",userRole)
  }
}
