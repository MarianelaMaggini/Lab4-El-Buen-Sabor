import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarReporteComponent } from './listar-reporte.component';

describe('ListarReporteComponent', () => {
  let component: ListarReporteComponent;
  let fixture: ComponentFixture<ListarReporteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarReporteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarReporteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
