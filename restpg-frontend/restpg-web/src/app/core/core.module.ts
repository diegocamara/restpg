import { NgModule } from "@angular/core";
import { AccountService } from "./service/account.service";
import { JWTService } from "./service/jwt.service";
import { IonicModule } from "@ionic/angular";
import { ErrorHandlerInterceptor } from "./interceptor/error-handler.interceptor";
import { ToastService } from "./service/toast.service";

@NgModule({
  providers: [AccountService, JWTService, ToastService],
})
export class CoreModule {}
