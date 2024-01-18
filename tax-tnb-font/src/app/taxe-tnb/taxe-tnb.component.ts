import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-taxe-tnb',
  templateUrl: './taxe-tnb.component.html',
  styleUrl: './taxe-tnb.component.css'
})
export class TaxeTnbComponent {
  categories: any[] = [];
  terrains: any[] = [];
  tauxs: any[] = [];
  taxeTnbs: any[] = [];
  selectedTaxe: any = {};
  newTaxe: any = {};
  hasToken: boolean = false;
  user: any = {};
  tokens: any = {};
  filteredTaxeTnbs: any[] = [];
  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private cdr: ChangeDetectorRef,
  ) {}
  filterTable(event: KeyboardEvent) {
    const value = (event.target as HTMLInputElement).value;
    this.filteredTaxeTnbs = this.taxeTnbs.filter(taxeTnb => taxeTnb.proprietaire.cin.includes(value));
  }
  ngOnInit(): void {
    this.http
      .get<any[]>('http://localhost:8222/api/main/category/findAll')
      .subscribe((data) => {
        this.categories = data;
        console.log(this.categories);
      });
    this.http
      .get<any[]>('http://localhost:8222/api/main/terrain/findAll')
      .subscribe((data) => {
        this.terrains = data;
      });
    this.http
      .get<any[]>('http://localhost:8222/api/main/taux/findAll')
      .subscribe((data) => {
        this.tauxs = data;
      });
    this.http
    .get<any[]>('http://localhost:8222/api/main/taxeTnb/findAll')
    .subscribe((data) => {
      this.taxeTnbs = data;
      this.filteredTaxeTnbs = this.taxeTnbs;
    });
      this.hasToken = this.cookieService.check('jwt');
    if (this.hasToken) {
      const token = this.cookieService.get('jwt');
      // Call the API to check token validity
      this.http
        .get(
          `http://localhost:8222/api/auth/user/check-token-validity/${token}`
        )
        .subscribe((response: any) => {
          if (response.message.trim().toLowerCase() === 'not valid') {
            // If the token is not valid, remove 'jwt' and refresh token from cookies
            this.cookieService.delete('jwt');
            this.cookieService.delete('refreshToken');
            location.reload();
          } else {
            const decodedToken = jwtDecode(token);
            this.tokens = decodedToken;
            this.getUserData();
          }
        });

      this.cdr.detectChanges();
    } else {
      console.log('Token not found. Please log in.');
    }
    }
    getUserData(): void {
      if (this.tokens.sub) {
        this.http
          .get(
            `http://localhost:8222/api/auth/user/findByEmail/${this.tokens.sub}`
          )
          .subscribe((response: any) => {
            this.user = response;
          });
      }
    }

    editTaxeTnb(taxe: any): void {
      this.http
        .get<any>('http://localhost:8222/api/main/taxeTnb/findById/' + taxe)
        .subscribe((data) => {
          this.selectedTaxe = data;
          console.log(this.selectedTaxe);
        });
    }
    removeTaxeTnb(terrain: any) {
      this.http
        .request(
          'DELETE',
          'http://localhost:8222/api/main/taxeTnb/delete/' + terrain.id
        )
        .subscribe(
          (response) => {
            window.location.reload();
          },
          (error) => {
            Swal.fire(
              'Error',
              'You cant delete this taxe as it contains terrains',
              'error'
            );
          }
        );
    }

    updateTaxeTnb() {
      console.log(this.selectedTaxe);
      this.http
        .post('http://localhost:8222/api/main/taxeTnb/save', this.selectedTaxe)
        .subscribe(
          (response) => {
            window.location.reload();
          },
          (error) => {
            Swal.fire(
              'Error',
              'An error occurred while updating terrain',
              'error'
            );
          }
        );
    }
    deleteTerrain() {
      this.http
        .request(
          'DELETE',
          'http://localhost:8222/api/main/terrain/delete/' +
            this.selectedTaxe.id
        )
        .subscribe(
          (response) => {
            window.location.reload();
          },
          (error) => {
            Swal.fire(
              'Error',
              'You cant delete this terrain as it contains taxes',
              'error'
            );
          }
        );
    }

    addTaxeTnb() {
      const deviceData = {
        ...this.newTaxe,
        categorie: { id: this.newTaxe.category },
        proprietaire: { id: this.user.id },
        terrain: { id: this.newTaxe.terrain },
        taux: { id: this.newTaxe.taux },
      };
      console.log(deviceData);
      this.http
        .post('http://localhost:8222/api/main/taxeTnb/save', deviceData)
        .subscribe(
          (response) => {
            window.location.reload();
          },
          (error) => {
            Swal.fire('Error', 'An error occurred while adding taxeTnb', 'error');
          }
        );
    }

  }
    
