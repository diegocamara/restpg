import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { CharactersPage } from "./characters.page";

const routes: Routes = [
  {
    path: "",
    component: CharactersPage,
  },
  {
    path: "new-character",
    loadChildren: () =>
      import("./new-character/new-character.module").then(
        (m) => m.NewCharacterPageModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CharactersPageRoutingModule {}
