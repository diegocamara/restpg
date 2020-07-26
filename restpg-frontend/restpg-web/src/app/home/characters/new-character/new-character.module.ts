import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NewCharacterPageRoutingModule } from './new-character-routing.module';

import { NewCharacterPage } from './new-character.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NewCharacterPageRoutingModule
  ],
  declarations: [NewCharacterPage]
})
export class NewCharacterPageModule {}
