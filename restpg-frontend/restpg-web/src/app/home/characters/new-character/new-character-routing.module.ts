import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { NewCharacterPage } from "./new-character.page";

const routes: Routes = [
  {
    path: "",
    component: NewCharacterPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewCharacterPageRoutingModule {}
