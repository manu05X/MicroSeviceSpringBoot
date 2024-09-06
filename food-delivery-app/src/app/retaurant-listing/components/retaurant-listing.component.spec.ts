import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetaurantListingComponent } from './retaurant-listing.component';

describe('RetaurantListingComponent', () => {
  let component: RetaurantListingComponent;
  let fixture: ComponentFixture<RetaurantListingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RetaurantListingComponent]
    });
    fixture = TestBed.createComponent(RetaurantListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
