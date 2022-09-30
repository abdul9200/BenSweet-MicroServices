import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CategoryService } from '../services/category.service';
import { PatisserieService } from '../services/patisserie.service';
import { ProductService } from '../services/product.service';
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products!:any;
  closeResult!:String;
  deleteId!:number;
  editForm!:FormGroup;
  cats!:any;
  patisseries!:any;

  constructor(private productService:ProductService,private httpClient:HttpClient,private modalService:NgbModal,private fb:FormBuilder) { }
  openEdit(targetModal:any, friend: any) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });


    this.editForm.patchValue( {
      id: friend?.id,
      designation: friend?.designation,
      description:friend?.description,
      currentPrice:friend?.currentPrice,
      promotion:friend?.promotion,
      selected:friend?.selected,
      available:friend?.available,
      quantityStock:friend?.quantityStock,
      quantitySold:friend?.quantitySold,


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
    this.productService.getProducts().subscribe(data=>{
      this.products=data
    });
    this.productService.getCategories().subscribe(data1=>{
      this.cats=data1;

    console.log(data1);});
      console.log(this.cats)
      this.productService.getPatisseries().subscribe(data2=>{
        this.patisseries=data2});
        console.log(this.patisseries)
        this.editForm=this.fb.group({

      id: [''],
      designation: [''],
      description:[''],
      currentPrice:[""],
      promotion:[''],
      selected:[''],
      available:[''],
      quantityStock:[''],
      quantitySold:[''],
      category:[''],
      patisserie:['']
        })
  }
  onDelete(){
    this.productService.deleteProduct(this.deleteId).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })
  }
  onSave(){
    const editURL = 'http://localhost:8084/products/' + this.editForm.value.id+'/patisserie/'+this.editForm.value.patisserie+'/categorie/'+this.editForm.value.category ;

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
    const url = 'http://localhost:8084/products/patisserie/'+f.value.patisserie+'/categorie/'+f.value.category;
    console.log(f.value)
    Reflect.deleteProperty(f.value,"patisserie")
    Reflect.deleteProperty(f.value,"category")
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
    document.getElementById("cdesignation")?.setAttribute("value",friend.designation);
    document.getElementById("cdescription")?.setAttribute("value",friend.description);
    document.getElementById("ccurrentPrice")?.setAttribute("value",friend.currentPrice);
    document.getElementById("cpromotion")?.setAttribute("value",friend.promotion);
    document.getElementById("cselected")?.setAttribute("value",friend.selected);
    document.getElementById("cavailable")?.setAttribute("value",friend.available);
    document.getElementById("cquantityStock")?.setAttribute("value",friend.quantityStock);
    document.getElementById("cquantitySold")?.setAttribute("value",friend.quantitySold);


  }


}
