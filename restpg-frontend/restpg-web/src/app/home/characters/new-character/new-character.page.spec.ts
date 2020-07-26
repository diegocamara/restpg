import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { NewCharacterPage } from './new-character.page';

describe('NewCharacterPage', () => {
  let component: NewCharacterPage;
  let fixture: ComponentFixture<NewCharacterPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewCharacterPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(NewCharacterPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
