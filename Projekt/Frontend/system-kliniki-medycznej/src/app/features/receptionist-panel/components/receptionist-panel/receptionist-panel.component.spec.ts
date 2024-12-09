import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceptionistPanelComponent } from './receptionist-panel.component';

describe('ReceptionistPanelComponent', () => {
  let component: ReceptionistPanelComponent;
  let fixture: ComponentFixture<ReceptionistPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReceptionistPanelComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceptionistPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
