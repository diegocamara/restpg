import { Injectable } from "@angular/core";

const tokenKey = "token";

@Injectable()
export class JWTService {
  storeToken(token: string) {
    window.localStorage.setItem(tokenKey, token);
  }

  getToken(): string {
    return window.localStorage.getItem(tokenKey);
  }

  removeToken() {
    window.localStorage.removeItem(tokenKey);
  }
}
