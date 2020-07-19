import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { AccountService } from "src/app/core/service/account.service";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.scss"],
})
export class SignUpComponent implements OnInit {
  signupForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountService
  ) {
    this.signupForm = this.formBuilder.group({
      username: ["", Validators.required],
      email: ["", Validators.required],
      password: ["", Validators.required],
    });
  }

  ngOnInit() {}

  signup() {
    this.accountService.signup(this.signupForm.value).subscribe(
      (signUpResponse) => {
        console.log(signUpResponse);
      },
      (error) => console.log(error)
    );
  }
}
