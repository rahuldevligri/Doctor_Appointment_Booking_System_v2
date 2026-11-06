import { ComponentFixture, TestBed } from '@angular/core/testing';

:src/app/schedule/schedule.component.spec.ts
import { ScheduleComponent } from './schedule.component';

describe('ScheduleComponent', () => {
  let component: ScheduleComponent;
  let fixture: ComponentFixture<ScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleComponent);

import { BackgroundComponent } from './background.component';

describe('BackgroundComponent', () => {
  let component: BackgroundComponent;
  let fixture: ComponentFixture<BackgroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BackgroundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackgroundComponent);
zsrc/app/background/background.component.spec.ts
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});