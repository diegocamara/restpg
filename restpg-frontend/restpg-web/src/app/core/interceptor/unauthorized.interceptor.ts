import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError, empty } from "rxjs";
import { JWTService } from "../service/jwt.service";
import { catchError } from "rxjs/operators";
import { ErrorResponse } from "../entity/error-response";
import { Router } from "@angular/router";
import { AccountService } from "../service/account.service";

@Injectable()
export class UnauthorizedInterceptor implements HttpInterceptor {
  constructor(private accountService: AccountService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next
      .handle(request)
      .pipe(
        catchError((httpErrorResponse) => this.errorHandler(httpErrorResponse))
      );
  }

  errorHandler(httpErrorResponse: HttpErrorResponse) {
    if (
      (!httpErrorResponse.url.includes("v1/signin") &&
        httpErrorResponse.status == 401) ||
      httpErrorResponse.status == 403
    ) {
      this.accountService.logout();
      return empty();
    }

    return throwError(httpErrorResponse);
  }
}
