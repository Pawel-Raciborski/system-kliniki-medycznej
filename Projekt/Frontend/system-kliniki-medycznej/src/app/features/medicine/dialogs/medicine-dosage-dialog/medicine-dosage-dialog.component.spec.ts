import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineDosageDialogComponent } from './medicine-dosage-dialog.component';

describe('MedicineDosageDialogComponent', () => {
  let component: MedicineDosageDialogComponent;
  let fixture: ComponentFixture<MedicineDosageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicineDosageDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicineDosageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
