import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { CategoriesComponent } from './categories/categories.component';
import { CategoryCrudComponent } from './category-crud/category-crud.component';
import { TauxComponent } from './taux/taux.component';
import { TauxCrudComponent } from './taux-crud/taux-crud.component';
import { TerrainComponent } from './terrain/terrain.component';
import { TerrainCrudComponent } from './terrain-crud/terrain-crud.component';
import { TaxeTnbComponent } from './taxe-tnb/taxe-tnb.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    CategoriesComponent,
    CategoryCrudComponent,
    TauxComponent,
    TauxCrudComponent,
    TerrainComponent,
    TerrainCrudComponent,
    TaxeTnbComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
