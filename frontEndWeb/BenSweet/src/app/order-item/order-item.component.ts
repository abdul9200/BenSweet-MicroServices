import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderItemService } from '../services/order-item.service';

@Component({
  selector: 'app-order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent implements OnInit {
  orderItems!:any ;
  closeResult!:String;
  deleteId!:number;
  editForm!:FormGroup;
  orders!:any;
  products!:any;

  constructor(private OrderItemService:OrderItemService,private httpClient:HttpClient,private modalService:NgbModal,private fb:FormBuilder) { }
  openEdit(targetModal:any, friend: any) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });


    this.editForm.patchValue( {
      id: friend?.id,
      product: friend?.product,
      productID:friend?.productID,
      quantity:friend?.quantity,
      price:friend?.price,
      order:friend?.order


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
    this.OrderItemService.getOrderItems().subscribe(data=>{
      this.orderItems=data;
    });
    this.OrderItemService.getProducts().subscribe(data1=>{
      this.products=data1;
    });
    this.OrderItemService.getOrders().subscribe(data2=>{
      this.orders=data2;
    });
    this.editForm=this.fb.group({
      id: [''],
      product: [''],
      productID:[''],
      quantity:[''],
      price:[''],
      order:['']
        })
  }
  onDelete(){
    this.OrderItemService.deleteOrderItem(this.deleteId).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })
  }
  onSave(){

    const editURL = 'http://localhost:8085/orderItems/' + this.editForm.value.id+'/order/'+this.editForm.value.order ;


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
    const url = 'http://localhost:8085/orderItems/'+f.value.order;
    console.log(f.value)
    Reflect.deleteProperty(f.value,"order")

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
    document.getElementById("id")?.setAttribute("value",friend.id);
    document.getElementById("cproduct")?.setAttribute("value",friend.product.designation);
    document.getElementById("cquantity")?.setAttribute("value",friend.quantity);
    document.getElementById("cprice")?.setAttribute("value",friend.price);
    document.getElementById("corder")?.setAttribute("value",friend.order.client);



  }


}
