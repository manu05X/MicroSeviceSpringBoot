import { Component } from '@angular/core';

@Component({
  selector: 'app-retaurant-listing',
  templateUrl: './retaurant-listing.component.html',
  styleUrls: ['./retaurant-listing.component.css']
})
export class RetaurantListingComponent {
  // public restaurantList: Restaurant[];

  // ngOnInit() {
  //   this.getAllRestaurants();
  // }

  // constructor(private router: Router, private restaurantService: RestaurantService) { }

  // getAllRestaurants() {
  //   this.restaurantService.getAllRestaurants().subscribe(
  //     data => {
  //       this.restaurantList = data;
  //     }
  //   )
  // }
  // getRandomNumber(min: number, max: number): number {
  //   return Math.floor(Math.random() * (max - min + 1)) + min;
  // }


  // getRandomImage(): string {
  //   const imageCount = 8; // Adjust this number based on the number of images in your asset folder
  //   const randomIndex = this.getRandomNumber(1, imageCount);
  //   return `${randomIndex}.jpg`; // Replace with your image filename pattern
  // }

  // onButtonClick(id: number) {
  //   this.router.navigate(['/food-catalogue', id]);
  // }

}
