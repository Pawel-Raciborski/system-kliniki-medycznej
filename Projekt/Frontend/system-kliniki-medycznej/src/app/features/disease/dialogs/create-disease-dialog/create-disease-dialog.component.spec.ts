import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDiseaseDialogComponent } from './create-disease-dialog.component';

describe('CreateDiseaseDialogComponent', () => {
  let component: CreateDiseaseDialogComponent;
  let fixture: ComponentFixture<CreateDiseaseDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateDiseaseDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateDiseaseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
