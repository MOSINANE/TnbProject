import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-terrain-crud',
  templateUrl: './terrain-crud.component.html',
  styleUrl: './terrain-crud.component.css',
})
export class TerrainCrudComponent {
  categories: any[] = [];
  terrains: any[] = [];
  selectedTerrain: any = {};
  newTerrain: any = {};
  hasToken: boolean = false;
  user: any = {};
  tokens: any = {};
  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private cdr: ChangeDetectorRef,
    
  ) {}
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
      location.href = '/';
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
  editTerrain(terrain: any): void {
    this.http
      .get<any>('http://localhost:8222/api/main/terrain/findById/' + terrain)
      .subscribe((data) => {
        this.selectedTerrain = data;
        console.log(this.selectedTerrain);
      });
  }

  removeTerrain(terrain: any) {
    this.http
      .request(
        'DELETE',
        'http://localhost:8222/api/main/terrain/delete/' + terrain.id
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

  updateTerrain() {
    console.log(this.selectedTerrain);
    this.http
      .post('http://localhost:8222/api/main/terrain/save', this.selectedTerrain)
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
        'http://localhost:8222/api/main/terrain/deleteById/' +
          this.selectedTerrain.id
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

  addTerrain() {
    const deviceData = {
      ...this.newTerrain,
      categorie: { id: this.newTerrain.category },
      proprietaire: { id: this.user.id }
    };
    console.log(deviceData);
    this.http
      .post('http://localhost:8222/api/main/terrain/save', deviceData)
      .subscribe(
        (response) => {
          window.location.reload();
        },
        (error) => {
          Swal.fire('Error', 'An error occurred while adding terrain', 'error');
        }
      );
  }
}
