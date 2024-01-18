import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CategoriesComponent } from './categories/categories.component';
import { TauxComponent } from './taux/taux.component';
import { TerrainComponent } from './terrain/terrain.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'category', component: CategoriesComponent },
  { path: 'taux', component: TauxComponent },
  { path: 'terrain', component: TerrainComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
