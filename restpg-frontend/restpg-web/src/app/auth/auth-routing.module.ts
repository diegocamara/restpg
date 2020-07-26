import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { SignInComponent } from "./signin/signin.component";
import { SignUpComponent } from "./signup/signup.component";
import { NoAuthGuard } from "./no-auth.guard";

const routes: Routes = [
  {
    path: "signin",
    component: SignInComponent,
    canActivate: [NoAuthGuard],
  },
  {
    path: "signup",
    component: SignUpComponent,
    canActivate: [NoAuthGuard],
  },
  {
    path: "**",
    redirectTo: "signin",
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
