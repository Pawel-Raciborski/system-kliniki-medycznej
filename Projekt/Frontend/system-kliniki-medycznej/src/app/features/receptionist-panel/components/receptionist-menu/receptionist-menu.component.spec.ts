import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceptionistMenuComponent } from './receptionist-menu.component';

describe('ReceptionistMenuComponent', () => {
  let component: ReceptionistMenuComponent;
  let fixture: ComponentFixture<ReceptionistMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReceptionistMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceptionistMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
