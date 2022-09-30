import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent } from './category/category.component';
import { CustomersComponent } from './customers/customers.component';
import { LivraisonComponent } from './livraison/livraison.component';
import { OrderItemComponent } from './order-item/order-item.component';
import { OrderComponent } from './order/order.component';
import { PatisserieComponent } from './patisserie/patisserie.component';
import { ProductsComponent } from './products/products.component';
import { RoleComponent } from './role/role.component';

const routes: Routes = [
  {path:"products" , component:ProductsComponent},
  {path:"user" , component:CustomersComponent},
  {path:"patisserie" , component:PatisserieComponent},
  {path:"order" , component:OrderComponent},
  {path:"livraison" , component:LivraisonComponent},
  {path:"category" , component:CategoryComponent},
  {path:"" , component:ProductsComponent},
  {path:"role",component:RoleComponent},
  {path:"orderItem",component:OrderItemComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
