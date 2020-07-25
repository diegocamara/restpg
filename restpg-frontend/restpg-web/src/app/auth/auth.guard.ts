import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from "@angular/router";
import { AccountService } from "../core/service/account.service";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private accountService: AccountService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.accountService.isAuthenticated()) {
      console.log("AuthGuard");
      this.router.navigate(["signin"]);
      return false;
    }

    return true;
  }
}
