import { ChangeDetectorRef, Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-taux-crud',
  templateUrl: './taux-crud.component.html',
  styleUrl: './taux-crud.component.css'
})
export class TauxCrudComponent {
  categories: any[] = [];
  rateForm: FormGroup;
  rates: any[] = [];
  selectedRate: any = {};
  newRate: any = {};
  filteredRates: any[] = [];

  constructor(
    private http: HttpClient,
    @Inject(FormBuilder) private fb: FormBuilder,
  ) {
    this.rateForm = this.fb.group({
      montant: '',
      categorie: ''
    });
  }
  filterTable(event: KeyboardEvent) {
    const value = (event.target as HTMLInputElement).value;
    this.filteredRates = this.rates.filter(rate => rate.categorie.type.includes(value));
  }
  ngOnInit() {
    this.http
    .get<any[]>('http://localhost:8222/api/main/category/findAll')
    .subscribe((data) => {
      this.categories = data;
    });

    this.http.get<any[]>('http://localhost:8222/api/main/taux/findAll').subscribe(data => {
      this.rates = data;
      this.filteredRates = this.rates;

    });
  }

  initializeForm(): void {
    this.rateForm = this.fb.group({
      montant: '',
      categorie: '',
    });
  }
  onSubmit() {
    const formData = this.rateForm.value;
    console.log('Form Data:', formData);
    const formattedData = {
      montant: formData.montant,
      categorie: { id: parseInt(formData.categorie) } // Assuming categorie is a number
    };
    this.http
      .post('http://localhost:8222/api/main/taux/save', formattedData)
      .subscribe((response) => {
        location.reload();
      });
  }


  editTaux(rate: any): void {
    this.http.get<any>('http://localhost:8222/api/main/taux/findById/' + rate).subscribe(data => {
      this.selectedRate = data;
      console.log(this.selectedRate);
    });
  }

  removeTaux(rate: any) {
    this.http.request('DELETE', 'http://localhost:8222/api/main/taux/delete/'+rate.id).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'You cant delete this rate as it contains categories', 'error');
    });
  }


  updateRate() {
    console.log(this.selectedRate);
    this.http.post('http://localhost:8222/api/main/category/save', this.selectedRate).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'An error occurred while updating Rate', 'error');
    });
  }
  deleteRate() {
    this.http.request('DELETE', 'http://localhost:8222/api/main/category/delete/'+this.selectedRate.id ).subscribe(response => {
      window.location.reload();
    }, error => {
      Swal.fire('Error', 'You cant delete this category as it contains devices', 'error');
    });
  }
}
