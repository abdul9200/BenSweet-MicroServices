import { Component, OnInit } from '@angular/core';
import {CustomerService} from 'src/app/services/customer.service';
import {ModalDismissReasons,NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup, NgForm ,ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers!:any;
  roles!:any;
  closeResult!:String;
  deleteId!:number;
  username!:String;
  editForm!:FormGroup;
  userRoles!:any;

  constructor(private customerService:CustomerService,private httpClient : HttpClient,private modalService :NgbModal,private fb:FormBuilder) { }
  openEdit(targetModal:any, friend: any) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
    console.log(this.editForm)

    this.editForm.patchValue( {
      id: friend?.id,
      username: friend?.username,
      email: friend?.email,
      telephone: friend?.telephone
    });
  }
  openDelete(targetModal:any, friend: any) {
     this.deleteId = friend.id;
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
  }
  onSave(){
    const editURL = 'http://localhost:8083/users/' + this.editForm.value.id ;

  this.httpClient.put(editURL, this.editForm.value)
    .subscribe((results) => {
      this.ngOnInit();
      this.modalService.dismissAll();
    });
  }
openRoles(targetModal:any,friend:any){
this.username=friend.username;
this.modalService.open(targetModal, {
  backdrop: 'static',
  size: 'lg'
});
this.customerService.getRoleofUser(friend.id).subscribe(data=>{

  this.userRoles=data;
});


}
  ngOnInit(): void {
    this.customerService.getUsers().subscribe(data=>{
      this.customers=data
    });
    this.customerService.getRoles().subscribe(data=>{
      this.roles=data

    })

    this.editForm = this.fb.group({
      id: [''],
      username: [''],
      email: [''],
      telephone: ['']
    } );
    this.username='';

  }
  onDelete(){
    console.log("supprime " +this.deleteId)
    this.customerService.deleteUser(this.deleteId).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })

  }
  onDeleteRole(username:any,role:any){
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        "username": username,
        "rolename": role
      },
    };


    this.customerService.RetrieveRoleFromUser(options).subscribe(response=>{
      this.ngOnInit();
      this.modalService.dismissAll();
    })
    console.log("aaa")
  }
  open(content:any){
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });

  }
  openRole(content:any,username: string){
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });

    document.getElementById("usernameRole")?.setAttribute("value",username);

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
  onSubmitRole(g:NgForm){
    const url = 'http://localhost:8083/addRoleToUser';

    this.httpClient.post(url, g.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal

  }

  onSubmit(f: NgForm) {
    const url = 'http://localhost:8083/users';
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
    document.getElementById("uname")?.setAttribute("value",friend.username);
    document.getElementById("email")?.setAttribute("value",friend.email);
    document.getElementById("Tel")?.setAttribute("value",friend.telephone);
  }

  handleUpdateUser(id : number){
    console.log("open update user pop-up to update user with id: " +id)

  }



}
