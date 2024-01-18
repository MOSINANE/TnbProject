import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TauxCrudComponent } from './taux-crud.component';

describe('TauxCrudComponent', () => {
  let component: TauxCrudComponent;
  let fixture: ComponentFixture<TauxCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TauxCrudComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TauxCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
