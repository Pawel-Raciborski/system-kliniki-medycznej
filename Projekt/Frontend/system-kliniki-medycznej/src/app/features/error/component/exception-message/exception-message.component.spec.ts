import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExceptionMessageComponent } from './exception-message.component';

describe('ExceptionMessageComponent', () => {
  let component: ExceptionMessageComponent;
  let fixture: ComponentFixture<ExceptionMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExceptionMessageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExceptionMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
