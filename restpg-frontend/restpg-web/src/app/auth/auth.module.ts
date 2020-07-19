import { NgModule } from "@angular/core";
import { AuthRoutingModule } from "./auth-routing.module";
import { SignInComponent } from "./signin/signin.component";
import { SignUpComponent } from "./signup/signup.component";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { IonicModule } from "@ionic/angular";
import { CommonModule } from "@angular/common";

@NgModule({
  imports: [AuthRoutingModule, CommonModule, IonicModule, ReactiveFormsModule],
  declarations: [SignInComponent, SignUpComponent],
  providers: [],
})
export class AuthModule {}
