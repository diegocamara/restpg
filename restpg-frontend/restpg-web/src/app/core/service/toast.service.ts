import { Injectable } from "@angular/core";
import { ToastController } from "@ionic/angular";
import { ErrorResponse } from "../entity/error-response";

@Injectable()
export class ToastService {
  constructor(public toastController: ToastController) {}

  async showErrors(errorResponse: ErrorResponse, duration: number) {
    const toast = await this.toastController.create({
      message: errorResponse.errors[0],
      duration: duration,
      color: "warning",
    });
    toast.present();
  }
}
