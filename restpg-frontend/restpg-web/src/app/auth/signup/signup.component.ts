import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { AccountService } from "src/app/core/service/account.service";
import { Router } from "@angular/router";
import { JWTService } from "src/app/core/service/jwt.service";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.scss"],
})
export class SignUpComponent implements OnInit {
  signupForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountService,
    private jwtService: JWTService,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      username: ["", Validators.required],
      email: ["", Validators.required],
      password: ["", Validators.required],
    });
  }

  ngOnInit() {}

  signup() {
    this.accountService.signUp(this.signupForm.value).subscribe(
      (signupResponse) => {
        this.jwtService.storeToken(signupResponse.token);
        this.router.navigate(["home"]);
      },
      (error) => console.log(error)
    );
  }
}
