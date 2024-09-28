# Introduction Of FrontEnd

- **app.module.ts** => This defines the root module for you named **App module** that tells Angular how to assemble your application together.

- Initially we declare only AppComponent.
  Then slowly as as we go on adding more and more components, we have to add those declarations here in the declarations tab as you go on increasing your angular Applications size.

- This particular file is very important for assembling the complete application as a bundle.

- **Bootstrapping (main.ts):**
  In computing, bootstrapping refers to a self-starting process that allows a system or application to initialize itself without external input. This can involve loading essential software or building a programming environment using existing code. For example, a bootstrap loader is the initial code that runs when a computer starts, responsible for loading the operating system. Additionally, in programming, bootstrapping can describe the process of writing a compiler in the same language it compiles, allowing for iterative improvements

- So bootstrapping is where the application is loaded when Angular comes into life.
  - So there was something called as **index.html** there we are getting into a script having **main.ts**.
    - In **index.html** we have <app-root></app-root> this refferd in app.component.ts.

```jS
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'food-delivery-app';
}

```

- So from **index.html** we are getting into **main.ts** file which is bootstrapping or initiating **AppModule** i.e **app.module.ts** file,
- **app.module.ts** is again bootstrapping **AppComponent** file.

So this is how the flow goes from _index.html_ **->** _main.ts_ **->** _app.module.ts_ **->** _AppComponent_.

- **AppComponent (app.component.ts):**
  It defines the logic of the app root component.
  The view associated with this root component becomes the root of the view hierarchy or the DOM.

  - This is responsiable for a part of screen that we see in front of us on the app.
  - In this we see two component:
    - app.component.html : templateUrl
    - app.component.css : styleUrls

- **app.component.html:** The next thing you can see here is a template, which is HTML file of app.component.HTML file. It defines the HTML associated with the root component.

  - index.html* **->** \_main.ts* **->** _app.module.ts_ **->** _AppComponent_ **->** _app.component.html_

- **app.component.spec.ts:** This is for unit testing your component files.

## NgModule (@NgModule)

Let's get deeper into App.module.ts file in NG module file that is app module, which is a root module for the application.

- ng modules are nothing but simple TypeScript classes which are decorated with _@NgModule_ decorator which is actually imported from angular code library.

- The purpose of this NgModule is to declare each thing you create in angular and group them together just like a Java packages.

- So modules provide a way for developers to organize their code and they are particularly very helpful when your application grows in your size and more and more components will be added.

- So your application always have at least one module, which is a root module, which is app module.

- We need to bootstrap the root module, at the start of the application which is in the main.ts file.

### Understand Declarations and imports

NgModule is defined by a class decorator @NgModule.The NG module decorator is a function that takes a single object.

- If you can see this particular thing takes a Json object, and this Json object contains the properties that describes the module.

```javaScript
@NgModule({
  declarations: [
    AppComponent
  ],
  exports: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderModule,
    RestaurantListingModule,
    HttpClientModule,
    FoodCatalogueModule,
    OrderSummaryModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
```

- The most important property is **declarations**. Declarations declares views to make them privately available in a module.

  - what all components you're going to make, you have to declare.
  - But just make sure that, this is private declaration.
  - Just to make the bundle out of it, but not making these components available to other modules.

- **export:** So if we want to make any of these module available to other module, there is another thing called as **export**.
- **import:** So for any component to work, it might require a help of other modules because many of the times you might need these things to embed into your current module or component.

  - So import is where you import other modules like _BrowserModule_,_AppRoutingModule_, _HeaderModule_, _platformDynamicModules_ and other modules.

- **providers:** The arrays of the services that you are going to inject in module.

> - The component is only the view, but the actual logic or connection to the backend and databases, everything is done in the something called as services.
> - These services injected into the components.
> - All the services you provide here in the provider are actually public and will be visible to all the other views too.

## Summary

- **declarations** is something which is _private_.
- **exports** is making your component _public_.
- **imports** is like importing other modules which is required for current module to work.

- **providers**, which is the _public services_ you require for your components.

- **Bootstrap**, is compiling and reading and actually creating an instance of it.
  - So in _AppModule_ we bootstrap the app component, which is the root component for this, which is an _entry point_ to the real logic or the part of the screen for your application.
