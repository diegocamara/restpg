import { NgModule } from "@angular/core";
import { AccountService } from "./service/account.service";
import { JWTService } from "./service/jwt.service";

@NgModule({
  providers: [AccountService, JWTService],
})
export class CoreModule {}
