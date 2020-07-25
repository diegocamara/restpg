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

@Injectable()
export class AccountService {
  constructor(private httpClient: HttpClient, private jwtService: JWTService) {}

  signIn(loginRequest: SigninRequest): Observable<SigninResponse> {
    return this.httpClient
      .post<SigninResponse>(
        `${environment.restpgApi.url}/v1/signin`,
        loginRequest
      )
      .pipe(catchError(this.errorHandler));
  }

  signUp(signupRequest: SignupRequest): Observable<SignupResponse> {
    return this.httpClient
      .post<SignupResponse>(
        `${environment.restpgApi.url}/v1/signup`,
        signupRequest
      )
      .pipe(catchError(this.errorHandler));
  }

  isAuthenticated(): boolean {
    return this.jwtService.getToken() !== null;
  }

  errorHandler(httpErrorResponse: HttpErrorResponse) {
    let errorResponse = new ErrorResponse();

    if (httpErrorResponse.error instanceof ErrorEvent) {
      //client-side error
      errorResponse.errors = [httpErrorResponse.error.message];
    } else {
      // server-side error
      errorResponse = httpErrorResponse.error;
    }

    return throwError(errorResponse);
  }
}
