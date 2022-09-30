import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PatisserieService {
  private url = "http://localhost:8084/patisseries/"
  constructor(private httpClient : HttpClient) { }
  getPatisseries(){

    return this.httpClient.get(this.url);
  }
  deletePatisserie(id:number){
    console.log("http://localhost:8084/Patisseries/"+id)
    return this.httpClient.delete("http://localhost:8084/Patisseries/"+id);
  }
  getPatissery(id:number){
    return this.httpClient.get(this.url+id);
  }
}
