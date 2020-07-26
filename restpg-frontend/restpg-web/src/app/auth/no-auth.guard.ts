import { Injectable } from "@angular/core";

import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanActivate,
  Router,
} from "@angular/router";
import { AccountService } from "../core/service/account.service";

@Injectable()
export class NoAuthGuard implements CanActivate {
  constructor(private accountService: AccountService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    console.log("NoAuthGuard", route);
    if (this.accountService.isAuthenticated()) {
      this.router.navigate(["home"]);
      return false;
    }
    return true;
  }
}
