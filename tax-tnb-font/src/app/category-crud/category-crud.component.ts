import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-category-crud',
  templateUrl: './category-crud.component.html',
  styleUrl: './category-crud.component.css'
})
export class CategoryCrudComponent {
  categories: any[] = [];
  selectedCategory: any = {};
  newCategory: any = {};
  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8222/api/main/category/findAll').subscribe(data => {
      this.categories = data;
    });
  }
  editCategory(category: any): void {
    this.http.get<any>('http://localhost:8222/api/main/category/findById/' + category).subscribe(data => {
      this.selectedCategory = data;
      console.log(this.selectedCategory);
    });
  }

  removeCategory(category: any) {
    this.http.request('DELETE', 'http://localhost:8222/api/main/category/delete/'+category.id).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'You cant delete this category as it contains devices', 'error');
    });
  }

  updateCategory() {
    console.log(this.selectedCategory);
    this.http.post('http://localhost:8222/api/main/category/save', this.selectedCategory).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'An error occurred while updating category', 'error');
    });
  }

  deleteCategory() {
    this.http.request('DELETE', 'http://localhost:8222/api/main/category/delete/'+this.selectedCategory.id ).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'You cant delete this category as it contains devices', 'error');
    });
  }


  addCategory() {
    this.http.post('http://localhost:8222/api/main/category/save', this.newCategory).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'An error occurred while adding category', 'error');
    });
  }
}
