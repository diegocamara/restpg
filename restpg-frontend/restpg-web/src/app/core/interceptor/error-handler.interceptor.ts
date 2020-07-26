import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { ErrorResponse } from "../entity/error-response";
import { catchError } from "rxjs/operators";
import { ToastService } from "../service/toast.service";
import { AccountService } from "../service/account.service";

@Injectable()
export class ErrorHandlerInterceptor implements HttpInterceptor {
  constructor(private toastService: ToastService) {}

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
    let errorResponse = new ErrorResponse();

    if (httpErrorResponse.error instanceof ErrorEvent) {
      //client-side error
      errorResponse.errors = [httpErrorResponse.error.message];
    } else {
      // server-side error
      errorResponse = httpErrorResponse.error;
      this.toastService.showErrors(errorResponse, 4000);
    }

    return throwError(errorResponse);
  }
}
