import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RetaurantListingRoutingModule } from './retaurant-listing-routing.module';
import { RetaurantListingComponent } from './components/retaurant-listing.component';


@NgModule({
  declarations: [
    RetaurantListingComponent
  ],
  imports: [
    CommonModule,
    RetaurantListingRoutingModule
  ],
  // exports:[RetaurantListingComponent]
})
export class RetaurantListingModule { }
