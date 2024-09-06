cd food-delivery-app/src/app -> ng g module header --routing
: for creating header module in app

Now we have header-routing.module.ts and header.module.ts

Now to make other components we do : ng g c header --module=header

Similarly we now make retaurant-listing module: ng g module retaurant-listing --routing
Now to make other components we do : ng g c retaurant-listing --module=retaurant-listing

ng g module food-catalogue --routing
ng g c food-catalogue --module=food-catalogue
