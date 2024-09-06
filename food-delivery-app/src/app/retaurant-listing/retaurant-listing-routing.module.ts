import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RetaurantListingComponent } from './components/retaurant-listing.component';


const routes: Routes = [
  { path: '', component: RetaurantListingComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RetaurantListingRoutingModule { }
