import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderService } from '../services/order.service';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders!:any;
  closeResult!:String;
  deleteId!:number;
  editForm!:FormGroup;
  users!:any;
  products!:any;
  ord!:any;

  constructor(private orderService:OrderService,private httpClient:HttpClient,private modalService:NgbModal,private fb:FormBuilder) { }
  openEdit(targetModal:any, friend: any) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });


    this.editForm.patchValue( {
      id: friend?.id,
      date: friend?.date,
      orderItem:friend?.orderItem,
      client:friend?.client,
      clientID:friend?.clientID,
      totalAmount:friend?.totalAmount



    });
  }
  openDelete(targetModal:any, friend: any) {
    this.deleteId = friend.id;
   this.modalService.open(targetModal, {
     backdrop: 'static',
     size: 'lg'
   });
 }

  ngOnInit(): void {
    this.orderService.getOrders().subscribe(data=>{
      this.orders=data
    });
    this.orderService.getProducts().subscribe(data1=>{
      this.products=data1;
    });
    this.orderService.getUsers().subscribe(data2=>{
      this.users=data2;
    });
    this.editForm=this.fb.group({
      id: [""],
      date: [''],
      orderItem:[''],
      client:[''],
      clientID:[''],
      totalAmount:['']
    });
  }
  onDelete(){
    this.orderService.deleteOrder(this.deleteId).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })
  }
  onSave(){
    const editURL = 'http://localhost:8085/orders/' + this.editForm.value.id+'/patisserie/'+this.editForm.value.patisserie+'/categorie/'+this.editForm.value.category ;
    Reflect.deleteProperty(this.editForm.value,"product")

  this.httpClient.put(editURL, this.editForm.value)
    .subscribe((results) => {
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
    const url = 'http://localhost:8085/orders';

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
    this.ord=friend;
    console.log(this.ord);

    document.getElementById("id")?.setAttribute("value",friend.id);
    document.getElementById("cdate")?.setAttribute("value",friend.date);
    document.getElementById("cclient")?.setAttribute("value",friend.client.username);
    document.getElementById("ctoltalAmount")?.setAttribute("value",friend.totalAmount);




  }

  handleDeleteOrder(id : number){
    console.log("supprime " +id)
    this.orderService.deleteOrder(id).subscribe(response=>{
      this.orders=this.orders.filter((item: { id: number; })=>item.id!==id);
    })

  }
  handleAddOrder(){
    console.log("open add category pop-up")

  }
  handleUpdateOrder(id : number){
    console.log("open update category pop-up to update category with id: " +id)

  }

}
