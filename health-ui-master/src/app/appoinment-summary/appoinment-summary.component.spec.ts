import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppoinmentSummaryComponent } from './appoinment-summary.component';

describe('AppoinmentSummaryComponent', () => {
  let component: AppoinmentSummaryComponent;
  let fixture: ComponentFixture<AppoinmentSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppoinmentSummaryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppoinmentSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
