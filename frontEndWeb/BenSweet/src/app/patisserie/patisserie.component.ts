import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CustomerService } from '../services/customer.service';
import { PatisserieService } from '../services/patisserie.service';
@Component({
  selector: 'app-patisserie',
  templateUrl: './patisserie.component.html',
  styleUrls: ['./patisserie.component.css']
})
export class PatisserieComponent implements OnInit {
  patisseries!:any;
  closeResult!:String;
  deleteId!:number;
  editForm!:FormGroup;
  users!:any;

  constructor(private patisserieService:PatisserieService,private customerService:CustomerService,private httpClient:HttpClient,private modalService:NgbModal,private fb:FormBuilder) { }
  openEdit(targetModal:any, friend: any) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });


    this.editForm.patchValue( {
      id: friend?.id,
      name: friend?.name,
      description:friend?.description,
      solde:friend?.solde,
      moderateur:friend?.moderateur,
      moderateurID:friend?.moderateurID

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
    this.patisserieService.getPatisseries().subscribe(data=>{
      this.patisseries=data
    });
    this.customerService.getUsers().subscribe(data=>{
      this.users=data;
    })
    this.editForm=this.fb.group({
      id: [''],
      name: [''],
      description:[''],
      solde:[''],
      moderateurID:[""],
      moderateur:['']
    })
  }
  onDelete(){
    this.patisserieService.deletePatisserie(this.deleteId).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })
  }
  onSave(){
    const editURL = 'http://localhost:8084/Patisseries/' + this.editForm.value.id ;

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
    const url = 'http://localhost:8084/Patisseries';
    console.log(f.value)
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
    document.getElementById("cname")?.setAttribute("value",friend.name);
    document.getElementById("cdescription")?.setAttribute("value",friend.description);


  }

}
