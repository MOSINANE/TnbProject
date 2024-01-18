import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerrainCrudComponent } from './terrain-crud.component';

describe('TerrainCrudComponent', () => {
  let component: TerrainCrudComponent;
  let fixture: ComponentFixture<TerrainCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TerrainCrudComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TerrainCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
