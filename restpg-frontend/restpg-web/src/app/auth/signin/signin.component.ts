import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { AccountService } from "../../core/service/account.service";
import { JWTService } from "src/app/core/service/jwt.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-signin",
  templateUrl: "./signin.component.html",
  styleUrls: ["./signin.component.scss"],
})
export class SignInComponent implements OnInit {
  authForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountService,
    private jwtService: JWTService,
    private router: Router
  ) {
    this.authForm = this.formBuilder.group({
      email: ["", Validators.required],
      password: ["", Validators.required],
    });
  }

  ngOnInit() {}

  onSubmit() {
    this.accountService.signIn(this.authForm.value).subscribe(
      (signinResponse) => {
        this.jwtService.storeToken(signinResponse.token);
        this.router.navigate(["home"]);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
