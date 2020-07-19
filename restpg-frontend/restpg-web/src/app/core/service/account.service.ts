import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError, retry, map } from "rxjs/operators";
import { ErrorResponse } from "../entity/error-response";
import { JWTService } from "./jwt.service";

@Injectable()
export class AccountService {
  serverUrl: String = "http://localhost:8080";
  constructor(private httpClient: HttpClient, private jwtService: JWTService) {}

  signup(signupRequest: any): Observable<any> {
    return this.httpClient
      .post(`${this.serverUrl}/v1/signup`, signupRequest)
      .pipe(
        map((signUpResponse: any) => {
          this.jwtService.storeToken(signUpResponse.token);
          return signUpResponse;
        }),
        catchError(this.errorHandler)
      );
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
