import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError, retry, map } from "rxjs/operators";
import { ErrorResponse } from "../entity/error-response";
import { JWTService } from "./jwt.service";
import { environment } from "../../../environments/environment";
import { SigninRequest } from "../entity/signin-request";
import { SigninResponse } from "../entity/signin-response";
import { SignupRequest } from "../entity/signup-request";
import { SignupResponse } from "../entity/signup-response";
import { Router } from "@angular/router";

@Injectable()
export class AccountService {
  constructor(
    private httpClient: HttpClient,
    private jwtService: JWTService,
    private router: Router
  ) {}

  signIn(loginRequest: SigninRequest): Observable<SigninResponse> {
    return this.httpClient.post<SigninResponse>(
      `${environment.restpgApi.url}/v1/signin`,
      loginRequest
    );
  }

  signUp(signupRequest: SignupRequest): Observable<SignupResponse> {
    return this.httpClient.post<SignupResponse>(
      `${environment.restpgApi.url}/v1/signup`,
      signupRequest
    );
  }

  logout() {
    this.jwtService.removeToken();
    this.router.navigate(["signin"]);
  }

  isAuthenticated(): boolean {
    return this.jwtService.getToken() !== null;
  }
}
