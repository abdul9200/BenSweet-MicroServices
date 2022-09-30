import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LivraisonService } from '../services/livraison.service';
@Component({
  selector: 'app-livraison',
  templateUrl: './livraison.component.html',
  styleUrls: ['./livraison.component.css']
})
export class LivraisonComponent implements OnInit {
  livraisons!:any;
  closeResult!:String;
  deleteId!:number;
  editForm!:FormGroup;
  orders!:any;
  user!:any;

  constructor(private livraisonService : LivraisonService,private httpClient:HttpClient,private modalService:NgbModal,private fb:FormBuilder) { }
  openEdit(targetModal:any, friend: any) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });


    this.editForm.patchValue( {
      name:friend?.name,
      adress: friend?.adress,
      livraisonEtatEnum:friend?.livraisonEtatEnum,
      order:friend?.order


    });
  }
  openDelete(targetModal:any, friend: any) {
    this.deleteId = friend.idLivraison;
   this.modalService.open(targetModal, {
     backdrop: 'static',
     size: 'lg'
   });
 }
 getUser(id:number){

  this.livraisonService.getClient(id).subscribe(data=>{
    this.user=data;
  });
  return this.user.username;
 }

  ngOnInit(): void {
    this.livraisonService.getLivraisons().subscribe(data=>
      this.livraisons=data);
      this.livraisonService.getOrders().subscribe(data1=>{
        this.orders=data1;
      });
      this.editForm= this.fb.group({
      name:[''],
      adress: [''],
      livraisonEtatEnum:[''],
      order:['']

      })
  }
  onDelete(){
    this.livraisonService.deleteLivraison(this.deleteId).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })

  }
  onSave(){
    const editURL='http://localhost:8085/livraisons/'+this.editForm.value.id+'/order/'+this.editForm.value.order;
    Reflect.deleteProperty(this.editForm.value,"order");
    this.httpClient.put(editURL,this.editForm.value).subscribe((results) => {
      this.ngOnInit();
      this.modalService.dismissAll();
    });


  }
  open(content:any){
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });

  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
  onSubmit(f: NgForm) {
    const url = 'http://localhost:8085/livraisons/'+f.value.order;
    Reflect.deleteProperty(f.value,"order");
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }
  openDetails(targetModal: any,friend:any){
    this.modalService.open(targetModal,{
      centered:true,
      backdrop:'static',
      size:"lg"
    });


    document.getElementById("id")?.setAttribute("value",friend.idLivraison);
    document.getElementById("cname")?.setAttribute("value",friend.name);
    document.getElementById("cadress")?.setAttribute("value",friend.adress);
    document.getElementById("cetat de livraison")?.setAttribute("value",friend.livraisonEtatEnum);
    document.getElementById("corder")?.setAttribute("value",friend.order.id+this.getUser(friend.order.clientID));




  }


  handleDeleteLivraison(id : number){
    console.log("supprime " +id)
    this.livraisonService.deleteLivraison(id).subscribe(response=>{
      this.livraisons=this.livraisons.filter((item: { id: number; })=>item.id!==id);
    })

  }
  handleAddLivraison(){
    console.log("open add Livraison pop-up")

  }
  handleUpdateLivraison(id : number){
    console.log("open update Livraison pop-up to update category with id: " +id)

  }


}
