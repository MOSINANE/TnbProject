import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TauxComponent } from './taux.component';

describe('TauxComponent', () => {
  let component: TauxComponent;
  let fixture: ComponentFixture<TauxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TauxComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TauxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
